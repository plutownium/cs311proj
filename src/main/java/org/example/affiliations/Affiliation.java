package org.example.affiliations;

public class Affiliation {
    private String affiliation = "";
    private int code = 0;

    public Affiliation(String party) {
        this.affiliation = party;
        if (party.equals("liberal")) {
            this.code = 1;
        } else if (party.equals("conservative")) {
            this.code = 2;
        } else if (party.equals("authoritarian")) {
            this.code = 3;
        } else if (party.equals("libertarian")) {
            this.code = 4;
        } else {
            System.out.println("Something went wrong setting affiliation");
            this.code = 0;
        }
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public int getCode() {
        return this.code;
    }
}
