Feature: Funkcja zakupu
  Scenario: Zakupienie bluzki
    Given Strona logowania sklepu CodersLab1
    When Loguje sie1
    When Wybiera do zakupu Hummingbird Printed Sweater
    And Wybiera rozmiar M i 5szt i dodaje do koszyka
    When Przechodzi do okna chceckout i potwierdza adres
    And Wybór metody odbioru i płatności
    Then Wykonuje screenshot potwierdzenia zamówienia
