Feature: Dodanie nowego adresu do konta

  Scenario Outline: Logowanie i dodawanie adresu
    Given Strona logowania sklepu CodersLab
    When Loguje sie
    When Przejście do strony adresu
    When Wypełnienie formularza danymi adresowymi "<alias>" "<address>" "<city>" "<zippostalcode>" "<country>" "<phone>"
    Then Sprawdzenie poprawności danych


    Examples:
    |alias|address|city|zippostalcode|country|phone|
    |JD   |Street 12|Nice|34-503 |United Kingdom |34652123|