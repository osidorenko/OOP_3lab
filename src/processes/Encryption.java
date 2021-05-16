package processes;


public class Encryption {
    private String text;
    private String encrypted = "";
    private int key;
    private char[][] crypt;

    public Encryption(String text) {
        this.text = text;
    }

    public Encryption(){

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public void setCrypt(int pkey) {
        if (pkey == 1) {
            encrypted = text;
            return;
        }
        createMatrix(pkey);
        this.key = pkey;
        String temp = "";
        for (int j = 0; j < pkey; j++) {
            for (int i = 0; i < text.length(); i++) {
                if (crypt[j][i] != '*') {
                    temp += crypt[j][i];
                }
            }
        }
        encrypted = temp;
        System.out.println("Шифровка при ключе " + pkey);
    }


    private void createMatrix(int pkey) {
        char tmp[] = text.toCharArray();
        crypt = new char[pkey][text.length()];
        for (int i = 0; i < pkey; i++) {
            for (int j = 0; j < text.length(); j++) {
                crypt[i][j] = '*';
            }
        }
        if (pkey >= 0) {
            int j = 0;
            int i = 0;
            int size = tmp.length;
            while (i < size) {
                while (j < pkey && i < size) {
                    crypt[j][i] = tmp[i];
                    i++;
                    j++;
                }
                j -= 2;
                while (j != 0 && i < size) {
                    crypt[j][i] = tmp[i];
                    i++;
                    j--;
                }
            }
        }
    }

    public String getCrypted(int k) {

        this.key = k;
        if (k == 1) {
            return encrypted;
        }
        char tmp[] = getEncrypted().toCharArray();
        crypt = new char[k][encrypted.length()];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < encrypted.length(); j++) {
                crypt[i][j] = '*';
            }
        }
        int dig[][] = new int[k][2];
        int sum = 2 * (k - 2);
        int temp = sum + 1;
        dig[0][0] = dig[0][1] = dig[k - 1][0] = dig[k - 1][1] = sum + 1;
        int z = 1;
        while (z < k - 1) {
            dig[z][0] = temp - 2;
            dig[z][1] = sum - dig[z][0];
            temp -= 2;
            z++;
        }
        z = 0;
        int defaulte = 0;
        for (int j = 0; j < k && z < tmp.length; j++) {
            int s = 0;
            int first = 1;
            crypt[j][defaulte++] = tmp[z++];
            for (int i = defaulte; i < tmp.length; i++) {
                if (s == dig[j][0] && first == 1 || s == dig[j][1] && first == -1) {
                    crypt[j][i] = tmp[z++];
                    first *= -1;
                    s = 0;
                } else {
                    s++;
                }
            }
        }
        String ret = "";
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < k; j++) {
                if (crypt[j][i] != '*') {
                    ret += crypt[j][i];
                }
            }
        }
        System.out.println("Дешифровка при ключе " + k + "\nтекста " + encrypted);
        return ret;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public String getText() {
        return text;
    }

    public String write() {
        char tmp[] = text.toCharArray();
        String s = "";
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < tmp.length; j++) {
                System.out.print(crypt[i][j] + " ");
                s += crypt[i][j] + " ";
            }
            s += "\n";
            System.out.println();
        }
        return s;
    }

}
