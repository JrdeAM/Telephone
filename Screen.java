import interfaces.PhoneObserver;

/**
 * Prints things out to the screen, when needed
 * Printing to the screen:
 * System.out.println("hello");
 */
public class Screen {
    private PhoneModel model;

    public Screen(PhoneModel model) {
        this.model = model;

        // Observador 1 - último dígito
        model.addObserver(new PhoneObserver() {

            @Override
            public void update() {

                int tamanho = model.getDigits().size();

                int ultimoDigito = model.getDigits()
                        .get(tamanho - 1);

                System.out.println(ultimoDigito);

            }

        });

        // Observador 2 - discando número completo
        model.addObserver(new PhoneObserver() {

            @Override
            public void update() {

                if (model.getDigits().size() == 12) {

                    String numero = "";

                    for (Integer digito : model.getDigits()) {
                        numero += digito;
                    }

                    System.out.println(
                            "Agora discando " + numero + "...");
                }
            }
        });
    }

}
