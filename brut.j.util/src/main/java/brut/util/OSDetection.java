/**
 *  Copyright (C) 2018 Ryszard Wiśniewski <brut.alll@gmail.com>
 *  Copyright (C) 2018 Connor Tumbleson <connor.tumbleson@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package brut.util;
import java.io.*;

public class OSDetection {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String Bit = System.getProperty("sun.arch.data.model").toLowerCase();
    private static String android_shell = "/system/bin/sh";

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMacOSX() {
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix") || (OS.contains("sunos")));
    }

    public static boolean is64Bit() {
        return Bit.equalsIgnoreCase("64");
    }

    public static String returnOS() {
        return OS;
    }
    public static boolean isAndroid() {
        return new File(android_shell).exists();
    }
    public static String android_arch() {
        if(new File(android_shell).exists()){
            try{
                InputStream inputStream = new FileInputStream(android_shell);
                byte[] bytes = new byte[20];
                inputStream.read(bytes);
                if(bytes[18] == (byte)3){
                    return "x86";
                }
                else if(bytes[18] == (byte)40){
                    return "arm";
                }
                else if(bytes[18] == (byte)62){
                    return "x86_64";
                }
                else if(bytes[18] == (byte)183){
                    return "aarch64";
                }
            }catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        return "unknown";   
    }

}
