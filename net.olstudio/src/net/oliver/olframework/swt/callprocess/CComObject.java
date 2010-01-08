package net.oliver.olframework.swt.callprocess;

import org.eclipse.swt.SWT;

import org.eclipse.swt.internal.ole.win32.COM;

import org.eclipse.swt.internal.ole.win32.DISPPARAMS;

import org.eclipse.swt.internal.ole.win32.EXCEPINFO;

import org.eclipse.swt.internal.ole.win32.GUID;

import org.eclipse.swt.internal.ole.win32.IDispatch;

import org.eclipse.swt.internal.ole.win32.ITypeInfo;

import org.eclipse.swt.internal.ole.win32.IUnknown;

import org.eclipse.swt.internal.win32.OS;

import org.eclipse.swt.ole.win32.OLE;

import org.eclipse.swt.ole.win32.Variant;

/**
 * 
 * @author hcw
 */

public class CComObject

{

	private GUID guid = new GUID();

	private IDispatch Autoface = null;

	private ITypeInfo TypeInfo = null;

	private void CreateComObject()

	{

		dispose();

		int[] ppv = new int[1];

		int ret = COM.CoCreateInstance(guid, 0, COM.CLSCTX_INPROC_SERVER

		| COM.CLSCTX_LOCAL_SERVER, COM.IIDIUnknown, ppv);

		if (ret != COM.S_OK)

			OLE.error(ret);

		// throw new RuntimeException("对象创建失败！");

		IUnknown objIUnknown = new IUnknown(ppv[0]);

		ppv = new int[1];

		ret = objIUnknown.QueryInterface(COM.IIDIDispatch, ppv);

		objIUnknown.Release();

		if (ret != COM.S_OK)

			OLE.error(ret);

		// throw new RuntimeException("对象不支持Dispatch！");

		Autoface = new IDispatch(ppv[0]);

		ppv = new int[1];

		ret = Autoface.GetTypeInfo(0, COM.LOCALE_USER_DEFAULT, ppv);

		if (ret == OLE.S_OK)

		{

			TypeInfo = new ITypeInfo(ppv[0]);

			TypeInfo.AddRef();

		}

	}

	private int getIDsOfNames(String name)

	{

		int[] rgdispid = new int[1];

		String[] names = new String[] { name };

		int ret = Autoface.GetIDsOfNames(guid, names, names.length,

		COM.LOCALE_USER_DEFAULT, rgdispid);

		if (ret != COM.S_OK)

			return -1;

		return rgdispid[0];

	}

	private void getVariantData(Variant aVar, int pData)

	{

		if (pData == 0)

			OLE.error(OLE.ERROR_OUT_OF_MEMORY);

		COM.VariantInit(pData);

		if ((aVar.getType() & COM.VT_BYREF) == COM.VT_BYREF)

		{

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8, new int[] { aVar.getByRef() }, 4);

			return;

		}

		switch (aVar.getType())

		{

		case COM.VT_EMPTY:

			break;

		case COM.VT_BOOL:

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8,

			new int[] { (aVar.getBoolean()) ? COM.VARIANT_TRUE

			: COM.VARIANT_FALSE }, 2);

			break;

		case COM.VT_R4:

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8, new float[] { aVar.getFloat() }, 4);

			break;

		case COM.VT_I4:

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8, new int[] { aVar.getInt() }, 4);

			break;

		case COM.VT_DISPATCH:

			aVar.getDispatch().AddRef();

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8, new int[] { aVar.getDispatch()

			.getAddress() }, 4);

			break;

		case COM.VT_UNKNOWN:

			aVar.getUnknown().AddRef();

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8, new int[] { aVar.getUnknown()

			.getAddress() }, 4);

			break;

		case COM.VT_I2:

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			COM.MoveMemory(pData + 8, new short[] { aVar.getShort() }, 2);

			break;

		case COM.VT_BSTR:

			COM.MoveMemory(pData, new short[] { aVar.getType() }, 2);

			char[] data = (aVar.getString() + "\0").toCharArray();

			int ptr = COM.SysAllocString(data);

			COM.MoveMemory(pData + 8, new int[] { ptr }, 4);

			break;

		default:

			OLE.error(SWT.ERROR_NOT_IMPLEMENTED);

		}

	}

	private Variant setVariantData(int pData)

	{

		if (pData == 0)

			OLE.error(OLE.ERROR_INVALID_ARGUMENT);

		Variant ret = null;

		short[] dataType = new short[1];

		COM.MoveMemory(dataType, pData, 2);

		int type = dataType[0];

		if ((type & COM.VT_BYREF) == COM.VT_BYREF)

		{

			int[] newByRefPtr = new int[1];

			OS.MoveMemory(newByRefPtr, pData + 8, 4);

			return new Variant(newByRefPtr[0], COM.VT_BYREF);

		}

		switch (type)

		{

		case COM.VT_EMPTY:

			break;

		case COM.VT_BOOL:

			short[] newBooleanData = new short[1];

			COM.MoveMemory(newBooleanData, pData + 8, 2);

			ret = new Variant(newBooleanData[0] != 0);

			break;

		case COM.VT_R4:

			float[] newFloatData = new float[1];

			COM.MoveMemory(newFloatData, pData + 8, 4);

			ret = new Variant(newFloatData[0]);

			break;

		case COM.VT_I4:

			int[] newIntData = new int[1];

			OS.MoveMemory(newIntData, pData + 8, 4);

			ret = new Variant(newIntData[0]);

			break;

		case COM.VT_DISPATCH:

		{

			int[] ppvObject = new int[1];

			OS.MoveMemory(ppvObject, pData + 8, 4);

			if (ppvObject[0] == 0)

			{

				type = COM.VT_EMPTY;

				break;

			}

			ret = new Variant(new IDispatch(ppvObject[0]));

			break;

		}

		case COM.VT_UNKNOWN:

		{

			int[] ppvObject = new int[1];

			OS.MoveMemory(ppvObject, pData + 8, 4);

			if (ppvObject[0] == 0)

			{

				type = COM.VT_EMPTY;

				break;

			}

			ret = new Variant(new IUnknown(ppvObject[0]));

			break;

		}

		case COM.VT_I2:

			short[] newShortData = new short[1];

			COM.MoveMemory(newShortData, pData + 8, 2);

			ret = new Variant(newShortData[0]);

			break;

		case COM.VT_BSTR:

			// get the address of the memory in which the string resides

			int[] hMem = new int[1];

			OS.MoveMemory(hMem, pData + 8, 4);

			if (hMem[0] == 0)

			{

				type = COM.VT_EMPTY;

				break;

			}

			int size = COM.SysStringByteLen(hMem[0]);

			if (size > 0)

			{

				char[] buffer = new char[(size + 1) / 2]; // add one to avoid

				// rounding errors

				COM.MoveMemory(buffer, hMem[0], size);

				ret = new Variant(new String(buffer));

			} else

			{

				ret = new Variant(""); //$NON-NLS-1$

			}

			break;

		default:

			int newPData = OS.GlobalAlloc(OS.GMEM_FIXED | OS.GMEM_ZEROINIT,

			Variant.sizeof);

			if (COM.VariantChangeType(newPData, pData, (short) 0, COM.VT_R4) == COM.S_OK)

			{

				ret = setVariantData(newPData);

			} else if (COM.VariantChangeType(newPData, pData, (short) 0,

			COM.VT_I4) == COM.S_OK)

			{

				setVariantData(newPData);

			} else if (COM.VariantChangeType(newPData, pData, (short) 0,

			COM.VT_BSTR) == COM.S_OK)

			{

				ret = setVariantData(newPData);

			}

			COM.VariantClear(newPData);

			OS.GlobalFree(newPData);

			break;

		}

		return ret;

	}

	private int invoke(int dispIdMember, int wFlags, Variant[] rgvarg,

	int[] rgdispidNamedArgs, Variant[] pVarResult)

	{

		if (Autoface == null)

			return COM.E_FAIL;

		DISPPARAMS pDispParams = new DISPPARAMS();

		if (rgvarg != null && rgvarg.length > 0)

		{

			pDispParams.cArgs = rgvarg.length;

			pDispParams.rgvarg = OS.GlobalAlloc(COM.GMEM_FIXED

			| COM.GMEM_ZEROINIT, Variant.sizeof * rgvarg.length);

			int offset = 0;

			for (int i = rgvarg.length - 1; i >= 0; i--)

			{

				getVariantData(rgvarg[i], pDispParams.rgvarg + offset);

				offset += Variant.sizeof;

			}

		}

		if (rgdispidNamedArgs != null && rgdispidNamedArgs.length > 0)

		{

			pDispParams.cNamedArgs = rgdispidNamedArgs.length;

			pDispParams.rgdispidNamedArgs = OS.GlobalAlloc(COM.GMEM_FIXED

			| COM.GMEM_ZEROINIT, 4 * rgdispidNamedArgs.length);

			int offset = 0;

			for (int i = rgdispidNamedArgs.length; i > 0; i--)

			{

				COM.MoveMemory(pDispParams.rgdispidNamedArgs + offset,

				new int[] { rgdispidNamedArgs[i - 1] }, 4);

				offset += 4;

			}

		}

		EXCEPINFO excepInfo = new EXCEPINFO();

		int[] pArgErr = new int[1];

		int pVarResultAddress = 0;

		if (pVarResult != null)

			pVarResultAddress = OS.GlobalAlloc(

			OS.GMEM_FIXED | OS.GMEM_ZEROINIT, Variant.sizeof);

		int ret = Autoface.Invoke(dispIdMember, new GUID(),

		COM.LOCALE_USER_DEFAULT, wFlags, pDispParams,

		pVarResultAddress, excepInfo, pArgErr);

		if (ret != COM.S_OK)

			return ret;

		if (pVarResultAddress != 0)

		{

			pVarResult[0] = setVariantData(pVarResultAddress);

			COM.VariantClear(pVarResultAddress);

			OS.GlobalFree(pVarResultAddress);

		}

		if (pDispParams.rgdispidNamedArgs != 0)

		{

			OS.GlobalFree(pDispParams.rgdispidNamedArgs);

		}

		if (pDispParams.rgvarg != 0)

		{

			int offset = 0;

			for (int i = 0, length = rgvarg.length; i < length; i++)

			{

				COM.VariantClear(pDispParams.rgvarg + offset);

				offset += Variant.sizeof;

			}

			OS.GlobalFree(pDispParams.rgvarg);

		}

		return ret;

	}

	/** */
	/**
	 * 
	 * 创建空对象在使用需调用CreateComObject或CreateComObjectProgID创建COM对象。
	 * 
	 * 
	 */

	public CComObject()

	{

	}

	/** */
	/**
	 * 
	 * 使用CLSID创建COM对象，建构后可以调用COM对象方法或属性。
	 * 
	 * 
	 * 
	 * @param sCLSID
	 * 
	 *            COM对象CLSID
	 * 
	 * @throws RuntimeException
	 */

	public CComObject(String sCLSID) throws RuntimeException

	{

		COM.IIDFromString(sCLSID.toCharArray(), guid);

		CreateComObject();

	}

	/** */
	/**
	 * 
	 * 使用现有自动化对象接口创建COM对象。
	 * 
	 * 
	 * 
	 * @param prgDispatch
	 */

	public CComObject(IDispatch prgDispatch)

	{

		Autoface = prgDispatch;

		Autoface.AddRef();

	}

	/** */
	/**
	 * 
	 * @author 杨中科
	 * 
	 * @param clientName
	 * 
	 * @param guid
	 */

	protected void getClassID(String clientName, GUID guid)

	{

		// create a null terminated array of char

		char[] buffer = null;

		if (clientName != null)

		{

			int count = clientName.length();

			buffer = new char[count + 1];

			clientName.getChars(0, count, buffer, 0);

		}

		if (COM.CLSIDFromProgID(buffer, guid) != COM.S_OK)

		{

			int result = COM.CLSIDFromString(buffer, guid);

			if (result != COM.S_OK)

				OLE.error(result);

		}

	}

	/** */
	/**
	 * 
	 * 使用CLSID创建COM对象。可用于创建新的对象（原有对象将释放）
	 * 
	 * 
	 * 
	 * @param sCLSID
	 * 
	 * @return
	 * 
	 * @throws RuntimeException
	 */

	public IDispatch CreateComObject(String sCLSID) throws RuntimeException

	{

		COM.IIDFromString(sCLSID.toCharArray(), guid);

		CreateComObject();

		return Autoface;

	}

	/** */
	/**
	 * 
	 * 创建远程COM对象（未实现）。
	 * 
	 * 
	 * 
	 * @param MachineName
	 * 
	 * @param sCLSID
	 * 
	 * @return
	 */

	public IDispatch CreateRemoteComObject(String MachineName, String sCLSID)

	{

		COM.IIDFromString(sCLSID.toCharArray(), guid);

		return Autoface;

	}

	/** */
	/**
	 * 
	 * 使用ProgID创建COM对象。
	 * 
	 * 
	 * 
	 * @param ProgID
	 * 
	 * @return
	 * 
	 * @throws RuntimeException
	 */

	public IDispatch CreateComObjectProgID(String ProgID)

	throws RuntimeException

	{

		getClassID(ProgID, guid);

		CreateComObject();

		return Autoface;

	}

	/** */
	/**
	 * 
	 * 调用COM对象方法，有返回值（可变参数暂不支持）。
	 * 
	 * 
	 * 
	 * @param FunctionName
	 * 
	 * @param rgvarg
	 * 
	 * @return
	 * 
	 * @throws RuntimeException
	 */

	public Variant CallFunction(String FunctionName, Variant[] rgvarg)

	throws RuntimeException

	{

		if (Autoface == null)

			throw new RuntimeException("对象为空");

		int rgdispid = getIDsOfNames(FunctionName);

		if (rgdispid == -1)

			throw new RuntimeException("方法不支持！");

		Variant[] pVarResult = new Variant[1];

		int ret = invoke(rgdispid, COM.DISPATCH_METHOD, rgvarg, null,

		pVarResult);

		if (ret != COM.S_OK)

			OLE.error(ret);

		// throw new RuntimeException("方法调用失败！");

		return pVarResult[0];

	}

	/** */
	/**
	 * 
	 * 调用COM对象方法，无参数和返回值。
	 * 
	 * 
	 * 
	 * @param FunctionName
	 * 
	 * @throws RuntimeException
	 */

	public void CallFunction(String FunctionName) throws RuntimeException

	{

		CallFunction(FunctionName, new Variant[] {});

	}

	/** */
	/**
	 * 
	 * 获取COM对象属性值。
	 * 
	 * 
	 * 
	 * @param PropertyName
	 * 
	 * @param rgvarg
	 * 
	 * @return
	 * 
	 * @throws RuntimeException
	 */

	public Variant getProperty(String PropertyName, Variant[] rgvarg)

	throws RuntimeException

	{

		if (Autoface == null)

			throw new RuntimeException("对象为空");

		int rgdispid = getIDsOfNames(PropertyName);

		if (rgdispid == -1)

			throw new RuntimeException("属性不支持！");

		Variant[] pVarResult = new Variant[1];

		int ret = invoke(rgdispid, COM.DISPATCH_PROPERTYGET, rgvarg, null,

		pVarResult);

		if (ret != COM.S_OK)

			OLE.error(ret);

		// throw new RuntimeException("属性获取失败");

		return pVarResult[0];

	}

	/** */
	/**
	 * 
	 * 给COM对象属性赋值。
	 * 
	 * 
	 * 
	 * @param PropertyName
	 * 
	 * @param rgvarg
	 * 
	 * @throws RuntimeException
	 */

	public void setProperty(String PropertyName, Variant[] rgvarg)

	throws RuntimeException

	{

		if (Autoface == null)

			throw new RuntimeException("对象为空");

		int rgdispid = getIDsOfNames(PropertyName);

		if (rgdispid == -1)

			throw new RuntimeException("属性不支持！");

		int[] rgdispidNamedArgs = new int[] { COM.DISPID_PROPERTYPUT };

		int dwFlags = COM.DISPATCH_PROPERTYPUT;

		for (int i = 0; i < rgvarg.length; i++)

		{

			if ((rgvarg[i].getType() & COM.VT_BYREF) == COM.VT_BYREF)

				dwFlags = COM.DISPATCH_PROPERTYPUTREF;

		}

		Variant[] pVarResult = new Variant[1];

		int ret = invoke(rgdispid, dwFlags, rgvarg, rgdispidNamedArgs,

		pVarResult);

		if (ret != COM.S_OK)

			OLE.error(ret);

	}

	/** */
	/**
	 * 
	 * 释放COM对象的连接。在对象不用的时候需调用此函数，以便及时释放对象。
	 * 
	 * 
	 */

	public void dispose()

	{

		if (Autoface != null)

			Autoface.Release();

		Autoface = null;

		if (TypeInfo != null)

			TypeInfo.Release();

		TypeInfo = null;

	}

}
