package models;

import java.util.*;

/**
 * Created by andreas on 2017/02/15.
 * DbFluteにより自動衛生されたものより抜粋
 */
public interface CDef extends Classification {

    /** The empty array for no sisters. */
    String[] EMPTY_SISTERS = new String[]{};

    /** The empty map for no sub-items. */
    @SuppressWarnings("unchecked")
    Map<String, Object> EMPTY_SUB_ITEM_MAP = (Map<String, Object>)Collections.EMPTY_MAP;

    /**
     * フラグ。共通的なフラグを示す区分値で、基本的にXxxフラグと呼べるものに関連付けられる。
     */
    public enum Flg implements CDef {
        /** Yes: フラグが True を示す (Yes) */
        True("Y", "Yes", new String[] {"true"})
        ,
        /** No: フラグが False を示す (No) */
        False("N", "No", new String[] {"false"})
        ;
        private static final Map<String, Flg> _codeValueMap = new HashMap<String, Flg>();
        static {
            for (Flg value : values()) {
                _codeValueMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisters()) { _codeValueMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private String[] _sisters;
        private Flg(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisters = sisters; }
        public String code() { return _code; } public String alias() { return _alias; }
        private String[] sisters() { return _sisters; }

        /**
         * Get the classification by the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static Flg codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof Flg) { return (Flg)code; }
            return _codeValueMap.get(code.toString().toLowerCase());
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The list of all classification elements. (NotNull)
         */
        public static List<Flg> listAll() {
            return new ArrayList<Flg>(Arrays.asList(values()));
        }

        @Override public String toString() { return code(); }
    }

    /**
     * 男と女。
     */
    public enum Gender implements CDef {
        /** 男 */
        Male("M", "男", EMPTY_SISTERS)
        ,
        /** 女 */
        Female("F", "女", EMPTY_SISTERS)
        ;
        private static final Map<String, Gender> _codeValueMap = new HashMap<String, Gender>();
        static {
            for (Gender value : values()) {
                _codeValueMap.put(value.code().toLowerCase(), value);
                for (String sister : value.sisters()) { _codeValueMap.put(sister.toLowerCase(), value); }
            }
        }
        private String _code; private String _alias; private String[] _sisters;
        private Gender(String code, String alias, String[] sisters)
        { _code = code; _alias = alias; _sisters = sisters; }
        public String code() { return _code; } public String alias() { return _alias; }
        private String[] sisters() { return _sisters; }

        /**
         * Get the classification by the code. (CaseInsensitive)
         * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
         * @return The instance of the corresponding classification to the code. (NullAllowed: if not found, returns null)
         */
        public static Gender codeOf(Object code) {
            if (code == null) { return null; }
            if (code instanceof Gender) { return (Gender)code; }
            return _codeValueMap.get(code.toString().toLowerCase());
        }

        /**
         * Get the list of all classification elements. (returns new copied list)
         * @return The list of all classification elements. (NotNull)
         */
        public static List<Gender> listAll() {
            return new ArrayList<Gender>(Arrays.asList(values()));
        }

        @Override public String toString() { return code(); }
    }

    public enum DefMeta implements ClassificationMeta {
        /**
         * EducationCd
         **/
        EducationCd,
        /**
         * フラグ。共通的なフラグを示す区分値で、基本的にXxxフラグと呼べるものに関連付けられる。
         */
        Flg
        //...
        ;

        @SuppressWarnings("unchecked")
        private List<Classification> toClassificationList(List<?> clsList) {
            return (List<Classification>) clsList;
        }

        public Classification codeOf(Object code) {
            if ("Flg".equals(name())) {
                return CDef.Flg.codeOf(code);
            }
            if ("Gender".equals(name())) {
                return CDef.Gender.codeOf(code);
            }
            //...
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }

        public List<Classification> listAll() {
            if ("Flg".equals(name())) {
                return toClassificationList(CDef.Flg.listAll());
            }
            if ("Gender".equals(name())) {
                return toClassificationList(CDef.Gender.listAll());
            }
            //...
            throw new IllegalStateException("Unknown definition: " + this); // basically unreachable
        }
    }
}
