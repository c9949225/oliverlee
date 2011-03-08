keytool -genkey -dname "CN=Email User, OU=ME, O=SouthEast University, L=GL, ST=Nanjing, C=CN" -alias email -keyalg RSA -keystore store1 -keypass kpass123 -storepass store1pass  -validity 3000

keytool -genkey -dname "CN=Webmaster, OU=NC, O=Shanghai University, L=ZB, ST=Shanghai, C=CN" -alias web -keyalg RSA -keystore store1 -keypass kpass123 -storepass store1pass  -validity 3000

keytool -genkey -dname "CN=Proxy User, OU=IC, O=Fudan University, L=ZB, ST=Shanghai, C=CN" -alias proxy -keyalg RSA -keystore store2 -keypass kpass123 -storepass store2pass -validity 3000
