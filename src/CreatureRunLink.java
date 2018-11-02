public class CreatureRunLink {
    private String runLink;
    private int maxX;
    private int maxY;

    public CreatureRunLink() {
        this.runLink = "http://pmav.eu/stuff/javascript-game-of-life-v3.1.1/?autoplay=0&trail=1&grid=1&colors=1&zoom=1&s=[";
        this.maxX = 179;
        this.maxY = 85;
    }

    public String createLink(boolean[][] dnaSequence) {
        StringBuilder builder = new StringBuilder();
        builder.append(this.runLink);

        int centerX = this.maxX / 2 - dnaSequence.length / 2;
        int centerY = this.maxY / 2 - dnaSequence[0].length / 2;

        boolean newHeight;
        for (int i = 0; i < dnaSequence.length; i++){
            boolean[] row = dnaSequence[i];
            newHeight = true;
            for (int j = 0; j < row.length; j++) {
                boolean piece = row[j];
                if (piece) {
                    int currX = j + centerX;
                    int currY = i + centerY;

                    if (newHeight) {
                        newHeight = false;
                        builder.append("{\"" + currY + "\":[" + currX);
                    } else
                        builder.append(","  + currX);
                }
            }

            if (!newHeight) {
                builder.append("]},");
            }
        }

        String url = builder.toString();
        url = url.substring(0, url.length() - 1);

        return url + ']';
    }
}
