package org.example.affiliations;

public class AffiliationIndexTool {
    public static int getIndexOf(String affiliation) {
        if (affiliation.equals("liberal")) {
            return 1;
        } else if (affiliation.equals("conservative")) {
            return 2;
        } else if (affiliation.equals("authoritarian")) {
            return 3;
        } else if (affiliation.equals("libertarian")) {
            return 4;
        }
        return 5;
    }
}
