package models;

/**
 * Created by andreas on 2017/02/15.
 * DbFluteのファイルより抜粋
 * original @author jflute
 */
public interface Classification {
    /**
     * Get the code of the classification.
     * @return The code of the classification. (NotNull)
     */
    String code();

    /**
     * Get the name, means identity name, of the classification.
     * @return The name of the classification. (NotNull)
     */
    String name();

    /**
     * Get the alias, means display name, of the classification.
     * @return The code of the classification. (NullAllowed: when an alias is not specified in its setting)
     */
    String alias();
}
