package net.oliver.olframework.util.datastructure.json;

import net.sf.json.JSONFunction;

class TestBean
{
    private String name = "json";

    private int pojoId = 1;

    private char[] options = new char[]
    { 'a', 'f' };

    private String func1 = "function(i){ return this.options[i]; }";

    private JSONFunction func2 = new JSONFunction(new String[]
    { "i" }, "return this.options[i];");

    public String getFunc1()
    {
        return func1;
    }

    public void setFunc1(String func1)
    {
        this.func1 = func1;
    }

    public JSONFunction getFunc2()
    {
        return func2;
    }

    public void setFunc2(JSONFunction func2)
    {
        this.func2 = func2;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public char[] getOptions()
    {
        return options;
    }

    public void setOptions(char[] options)
    {
        this.options = options;
    }

    public int getPojoId()
    {
        return pojoId;
    }

    public void setPojoId(int pojoId)
    {
        this.pojoId = pojoId;
    }

}