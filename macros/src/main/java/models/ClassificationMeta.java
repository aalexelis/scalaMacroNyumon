package models;

import java.util.List;

/**
 * Created by andreas on 2017/02/15.
 * DbFluteのファイルより抜粋
 * original @author jflute
 */
public interface ClassificationMeta {

    /**
     * Get classification by the code.
     * @param code The value of code, which is case-insensitive. (NullAllowed: if null, returns null)
     * @return The instance of the classification. (NullAllowed: when not found and code is null)
     */
    Classification codeOf(Object code);

    /**
     * Get the list of all classification elements. (returns new copied list)
     * @return The list of classification elements. (NotNull)
     */
    List<Classification> listAll();
}
