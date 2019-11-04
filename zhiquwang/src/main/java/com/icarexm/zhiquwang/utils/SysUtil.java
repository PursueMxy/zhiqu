package com.icarexm.zhiquwang.utils;

import android.annotation.SuppressLint;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SysUtil {
    public SysUtil() {
    }

    public static String generateTimespan() {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }

    public static String generateNonce() {
        UUID uuid;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            uuid = UUID.randomUUID();
            String guidStr = uuid.toString();
            md.update(guidStr.getBytes(), 0, guidStr.length());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch (NoSuchAlgorithmException var3) {
            uuid = UUID.randomUUID();
            return uuid.toString().replaceAll("\\-", "");
        }
    }



    public static boolean isNotNUll(Object object) {
        if (object instanceof CharSequence && ((CharSequence)object).length() > 0) {
            return true;
        } else if (object instanceof Collection) {
            return !((Collection)object).isEmpty();
        } else if (object instanceof Map) {
            return !((Map)object).isEmpty();
        } else {
            return object instanceof Object[] && ((Object[])object).length > 0;
        }
    }

    public static String changeMapCoding(Map map, Object key, String currentCoding, String targetCoding) {
        try {
            return map.get(key) != null ? new String(map.get(key).toString().getBytes(currentCoding), targetCoding) : null;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String changeStringCoding(String str, String currentCoding, String targetCoding) {
        if (str != null) {
            try {
                String result = new String(str.getBytes(currentCoding), targetCoding);
                return result;
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return str;
    }

    public static String changeStringCoding(String str, String targetCoding) {
        if (str != null) {
            try {
                String result = new String(str.getBytes(), targetCoding);
                return result;
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        return str;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isUserName(String username) {
        return Pattern.matches("([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+", username);
    }

    public static boolean isPassword(String password) {
        return Pattern.matches("^[a-zA-Z0-9]{6,16}$", password);
    }

    public static boolean isMobile(String mobile) {
        return Pattern.matches("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", mobile);
    }

    public static boolean isEmail(String email) {
        return Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", email);
    }

    public static boolean isChinese(String chinese) {
        return Pattern.matches("^[一-龥]{1,9}$", chinese);
    }

    public static boolean isIDCard(String idCard) {
        return Pattern.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])", idCard);
    }

    public static String getBirthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < 15) {
            return null;
        } else {
            if (len == 15) {
                idCard = conver15CardTo18(idCard);
            }

            String str = idCard.substring(6, 14);
            Date date = null;

            try {
                date = (new SimpleDateFormat("yyyyMMdd")).parse(str);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
        }
    }

    public static String conver15CardTo18(String idCard) {
        String idCard18 = "";
        if (idCard.length() != 15) {
            return null;
        } else if (isNumeric(idCard)) {
            String birthday = idCard.substring(6, 12);
            Date birthDate = null;

            try {
                birthDate = (new SimpleDateFormat("yyMMdd")).parse(birthday);
            } catch (ParseException var10) {
                var10.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            if (birthDate != null) {
                cal.setTime(birthDate);
            }

            @SuppressLint("WrongConstant") String sYear = String.valueOf(cal.get(1));
            idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
            char[] cArr = idCard18.toCharArray();
            if (cArr != null) {
                int[] iCard = converCharToInt(cArr);
                int iSum17 = getPowerSum(iCard);
                String sVal = getCheckCode18(iSum17);
                if (sVal.length() <= 0) {
                    return null;
                }

                idCard18 = idCard18 + sVal;
            }

            return idCard18;
        } else {
            return null;
        }
    }

    public static int[] converCharToInt(char[] ca) {
        int len = ca.length;
        int[] iArr = new int[len];

        try {
            for(int i = 0; i < len; ++i) {
                iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
            }
        } catch (NumberFormatException var4) {
            var4.printStackTrace();
        }

        return iArr;
    }

    public static int getPowerSum(int[] iArr) {
        int[] power = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int iSum = 0;
        if (power.length == iArr.length) {
            for(int i = 0; i < iArr.length; ++i) {
                for(int j = 0; j < power.length; ++j) {
                    if (i == j) {
                        iSum += iArr[i] * power[j];
                    }
                }
            }
        }

        return iSum;
    }

    public static String getCheckCode18(int iSum) {
        String sCode = "";
        switch(iSum % 11) {
            case 0:
                sCode = "1";
                break;
            case 1:
                sCode = "0";
                break;
            case 2:
                sCode = "x";
                break;
            case 3:
                sCode = "9";
                break;
            case 4:
                sCode = "8";
                break;
            case 5:
                sCode = "7";
                break;
            case 6:
                sCode = "6";
                break;
            case 7:
                sCode = "5";
                break;
            case 8:
                sCode = "4";
                break;
            case 9:
                sCode = "3";
                break;
            case 10:
                sCode = "2";
        }

        return sCode;
    }

    public static boolean isUrl(String url) {
        return Pattern.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url);
    }

    public static boolean isIPAddress(String ipAddress) {
        return Pattern.matches("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})", ipAddress);
    }

    public static boolean startPing(String ip) {
        boolean success = false;
        Process p = null;

        try {
            p = Runtime.getRuntime().exec("ping -c 1 -i 0.2 -w 1 " + ip);
            int status = p.waitFor();
            if (status == 0) {
                success = true;
            } else {
                success = false;
            }
        } catch (IOException var8) {
            success = false;
        } catch (InterruptedException var9) {
            success = false;
        } finally {
            p.destroy();
        }

        return success;
    }

    public static void deleteAllFiles(File root) {
        File[] files = root.listFiles();
        if (files != null) {
            File[] var5 = files;
            int var4 = files.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                File f = var5[var3];
                if (f.isDirectory()) {
                    deleteAllFiles(f);

                    try {
                        f.delete();
                    } catch (Exception var8) {
                    }
                } else if (f.exists()) {
                    deleteAllFiles(f);

                    try {
                        f.delete();
                    } catch (Exception var7) {
                    }
                }
            }
        }

    }

    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();

        for(int i = 0; i < chars.length; ++i) {
            if (i != chars.length - 1) {
                sbu.append(chars[i]).append(",");
            } else {
                sbu.append(chars[i]);
            }
        }

        return sbu.toString();
    }

    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");

        for(int i = 0; i < chars.length; ++i) {
            sbu.append((char)Integer.parseInt(chars[i]));
        }

        return sbu.toString();
    }
}
