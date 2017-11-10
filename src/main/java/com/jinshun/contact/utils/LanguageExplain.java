package com.jinshun.contact.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class LanguageExplain {

    private String regex = "(\\|@)|(@\\|)";// 分割解析角色字符串的正则表达�?

    private String ROLE_NAME_REGEX = "\\|@\\w*@.*\\|@";// 用于判断角色名称的字符串是否符合制定的格式规范的正则表达�?

    private static LanguageExplain languageExplain;

    private LanguageExplain() {

    }

    public static LanguageExplain getInstance() {
        if (languageExplain == null)
            languageExplain = new LanguageExplain();
        return languageExplain;
    }

    /**
     * 获取当前系统默认语言
     *
     * @param roleClob
     * @return
     */
    public static String getDefualtLanguage() {
        return Locale.getDefault().getLanguage() + "_"
                + Locale.getDefault().getCountry();
    }

    /**
     * 获取当前语言下的角色名称
     *
     * @param roleClob
     * @return
     */
    public String getCurrentRoleName(String roleClob) {
        return doGetRoleNameByLanguageType(roleClob,
                getDefualtLanguage());
    }

    /**
     * 获取指定语言下的角色名称
     *
     * @param roleClob
     * @param languageType
     * @return
     */
    public String getRoleNameByLanguageType(String roleClob, String languageType) {
        return doGetRoleNameByLanguageType(roleClob, languageType);
    }

    /**
     * 验证角色名称TEXT是否符合制定标准规范，�?�过与正则表达式匹配实现
     *
     * @param roleClob
     * @return true符合, false不符�?
     */
    public boolean matchRoleName(String roleClob) {
        if (roleClob == null || roleClob.equals(""))
            return false;
        else {
            return roleClob.matches(ROLE_NAME_REGEX);
        }
    }

    /**
     * @param roleString |@zh_CN@职员层@||@en_US@Staff@|
     * @return
     */
    private String doGetRoleNameByLanguageType(String roleClob,
                                               String languageType) {
        String roleName = "";
        if (roleClob != null && !roleClob.equals("")) {
            String[] roleNameList = roleClob.split(regex);
            boolean isExist = false;// 是否存在指定语言类型的多语言信息
            for (String nameType : roleNameList) {
                if (!nameType.equals("") && nameType.indexOf('@') != -1) {
                    if (nameType.indexOf(languageType) != -1) {
                        isExist = true;
                        roleName = nameType
                                .substring(nameType.indexOf('@') + 1);
                        break;
                    }
                }
            }
            if (!isExist) {
                // 如果不存在指定语�?的信息，则随机获取多语言信息
                if (roleNameList.length > 1) {
                    roleName = roleNameList[1].substring(roleNameList[1]
                            .indexOf('@') + 1);
                }
            }
        }
        roleName = reconvertRoleName(roleName);
        return roleName;
    }

    /**
     * 将角色名称中用户输入的特殊字符转换为标准字符
     *
     * @param roleName
     * @return
     */
    @SuppressWarnings("unused")
    private String convertRoleName(String roleName) {
        if (roleName == null || roleName.equals(""))
            return roleName;
        else {
            String newName = roleName.replace("@", "\\@");
            newName = newName.replace("|", "\\|");
            return newName;
        }
    }

    /**
     * 将数据库取出来的含有特殊格式的角色名称转换为正常格式
     *
     * @param roleName
     * @return
     */
    private String reconvertRoleName(String roleName) {
        if (roleName == null || roleName.equals(""))
            return roleName;
        else {
            String newName = roleName.replace("\\@", "@");
            newName = newName.replace("\\|", "|");
            return newName;
        }
    }

    /**
     * 将名称解析成多语�?实体
     *
     * @param roleMain
     * @return
     */
    public List<LanguageEntity> getLanguageListByMultiName(String multiName) {
        List<LanguageEntity> languageList = new ArrayList<LanguageEntity>();
        String[] roleNameList = multiName.split(regex);
        LanguageEntity roleLanguage;
        for (String nameType : roleNameList) {
            int index = nameType.indexOf('@');
            if (!nameType.equals("") && index != -1) {
                roleLanguage = new LanguageEntity();
                roleLanguage.setLanguageType(nameType.substring(0, index));
                roleLanguage.setMultiName(reconvertRoleName(nameType
                        .substring(index + 1)));
                languageList.add(roleLanguage);
            }
        }
        return languageList;
    }

    /**
     * 获取正则表达�?
     *
     * @return
     */
    public String getRegex() {
        return regex;
    }

}
