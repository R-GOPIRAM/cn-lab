import java.io.*;

class crc_gen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] data;
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;
        int data_bits = 0, divisor_bits = 0, tot_length;

        System.out.println("Enter number of data bits: ");
        while (true) {
            try {
                data_bits = Integer.parseInt(br.readLine());
                if (data_bits > 0) break;
                else System.out.println("Please enter a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        data = new int[data_bits];

        System.out.println("Enter data bits: ");
        for (int i = 0; i < data_bits; i++) {
            while (true) {
                try {
                    data[i] = Integer.parseInt(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }
        }

        System.out.println("Enter number of bits in divisor: ");
        while (true) {
            try {
                divisor_bits = Integer.parseInt(br.readLine());
                if (divisor_bits > 0) break;
                else System.out.println("Please enter a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        divisor = new int[divisor_bits];

        System.out.println("Enter Divisor bits: ");
        for (int i = 0; i < divisor_bits; i++) {
            while (true) {
                try {
                    divisor[i] = Integer.parseInt(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }
        }

        System.out.print("Data bits are: ");
        for (int i = 0; i < data_bits; i++) {
            System.out.print(data[i]);
        }
        System.out.println();

        System.out.print("Divisor bits are: ");
        for (int i = 0; i < divisor_bits; i++) {
            System.out.print(divisor[i]);
        }
        System.out.println();

        tot_length = data_bits + divisor_bits - 1;
        div = new int[tot_length];
        rem = new int[tot_length];
        crc = new int[tot_length];

        // CRC GENERATION
        for (int i = 0; i < data.length; i++) {
            div[i] = data[i];
        }

        System.out.print("Dividend (after appending 0's) is: ");
        for (int i = 0; i < div.length; i++) {
            System.out.print(div[i]);
        }
        System.out.println();

        for (int j = 0; j < div.length; j++) {
            rem[j] = div[j];
        }

        rem = divide(div, divisor, rem);

        for (int i = 0; i < div.length; i++) {
            crc[i] = (div[i] ^ rem[i]);
        }

        System.out.println();
        System.out.println("CRC code:");
        for (int i = 0; i < crc.length; i++) {
            System.out.print(crc[i]);
        }

        // ERROR DETECTION
        System.out.println();
        System.out.println("Enter CRC code of " + tot_length + " bits: ");
        for (int i = 0; i < crc.length; i++) {
            while (true) {
                try {
                    crc[i] = Integer.parseInt(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }
        }

        rem = new int[tot_length]; // Reset remainder for error detection
        for (int j = 0; j < crc.length; j++) {
            rem[j] = crc[j];
        }

        rem = divide(crc, divisor, rem);

        boolean error = false;
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                System.out.println("Error detected.");
                error = true;
                break;
            }
        }
        if (!error) {
            System.out.println("No Error detected.");
        }
        System.out.println("Thank you.");
    }

    static int[] divide(int[] div, int[] divisor, int[] rem) {
        int cur = 0;
        while (true) {
            for (int i = 0; i < divisor.length; i++) {
                rem[cur + i] = (rem[cur + i] ^ divisor[i]);
            }
            while (rem[cur] == 0 && cur != rem.length - 1) {
                cur++;
            }
            if ((rem.length - cur) < divisor.length) {
                break;
            }
        }
        return rem;
    }
}
