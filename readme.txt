Předmět:    ITU - Tvorba uživatelských rozhraní
Název:      Generátor hesla
Autor:      Pavel Bobčík (xbobci03)
Organizace: Vysoké učení technické v Brně

Zdrojové soubory se nachází ve složkách:
 - Java (MVC):                  app\src\main\java\com\bobcikprogramming\genertorhesla
 - XML:        
    - animace:                  app\src\main\res\anim
    - GUI oken:                 app\src\main\res\layout
    - ikony:                    app\src\main\res\drawable
    - barvy, styly, stringy:    app\src\main\res\values  
    - spodní lišty:             app\src\main\res\menu
 - JSON:                        app\src\main\assets

Instalace aplikace:
 - Pomocí balíčku APK
    - balíček .apk nalezneme ve složce app\build\outputs\apk\debug (app-debug.apk)
    - otevřeme balíček .apk na mobilním telefonu s operačním systémem Android (6.0 a vyšší)
    - v případě vyžádání povolíme 'instalovat aplikace z neznámých vzorů'
        - tento krok je nutný z důvodu, že vytvořený balíček .apk není podepsán
    - nainstalujeme aplikaci
    - nyní ji lze spustit

 - V případě sestavení pomocí vývojového prostředí:
    - budme potřebovat nástroj Android Studio: https://developer.android.com/studio
    - rozbalené programové řešení otevřeme v Android studiu
    - počkáme, než se nám projekt načte
    - vytvoříme si virtuální zařízení (s Androidem 6.0 a vyšší)
        - pomocí nástroje AVD manager jenž je součástí Android studia
    - další možností je připojit mobilní zařízení s operačním systémem Android (6.0 a vyšší)
        - je třeba povolit režim pro vývojáře
            - Nastavení > Systém > O telefonu > zde budeme klikat na číslo sestavení, dokud se nestaneme vývojářem
        - dále je třeba nastavit ladění pomocí USB
            - Nastavení > Další nastavení > Možnosti pro vývojáře > Ladění USB a Instalovat přes USB
    - vybereme námi vytvořený virtuální stroj nebo připojený fyzický mobilní telefon
    - sestavíme projekt tlačítkem build nebo kombinací kláves shift + f10
    - nyní by se nám měla nainstalovat a spustit aplikace
    
Knihovny:
 - Všechny použité knihovny jsou součástí Android Jetpack a jsou implementovány v souboru build.gradle (netřeba je dodatečně stahovat)
    - https://developer.android.com/jetpack/androidx
 