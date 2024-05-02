public class ReadCSVUtils {
    public static ParsedColumns generateParsedColumns(String[] entry) {
        ParsedColumns pc = new ParsedColumns();
        pc.firstName = StringUtils.toTitleCase(
                StringUtils.removeLeadingOrTrailingSpaces(
                        FieldUtils.parseFirstName(entry)
                    )
            );
        pc.lastName = StringUtils.toTitleCase(
                StringUtils.removeLeadingOrTrailingSpaces(
                        FieldUtils.parseLastName(entry)
                    )
            );
        pc.email = FieldUtils.parseEmail(entry);
        pc.companyName = StringUtils.toTitleCase(
                StringUtils.removeLeadingOrTrailingSpaces(
                        FieldUtils.parseCompanyName(entry)
                    )
            );
        pc.url = FieldUtils.parseURL(entry);
        return pc;
    }

    // check to see if any of the fields collected make the entry invalid, as defined by logic in each function. Skips invalid entries
    public static boolean validateFieldsForOutput(ParsedColumns pc) {
        return !(pc.firstName.length() < 4 || pc.lastName.length() < 4 || pc.email == "abort" || pc.companyName.length() < 4);
    }

    public static boolean isEntryViable(String[] e) {
        return e.length >= 20;
    }

    public static String buildOutputEntry(ParsedColumns pc) {
        return ((pc.firstName + "," + 
        pc.lastName + "," + 
        pc.email + "," + 
        pc.companyName + "," + 
        pc.url).replace("\"", "")).replace("  ", " ");
    }
}
