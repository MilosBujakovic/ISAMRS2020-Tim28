# ISAMRS2020-Tim28
Projekat iz predmeta Internet Softverske Arhitekture i Metodologije Razvoja Softvera


Tim 28
Članovi:
Slaven Garić SW-47/2015
Miloš Bujaković SW-58/2015


Pokretanje bekenda projekta iz radnog okruzenja Intellij-a vrši se kroz Maven/Plugins/spring-boot/spring-boot:run.
Za bazu je potrebna MySQL verzija 8.0 ili starija, username za bazu je "root", a za password "mysqlroot".

Prilikom pokretanja projekta vrši se dodavanje dummy podataka u bazu. Nakon toga moguće je otvoriti početnu stranicu u browseru
kucanjem http://localhost:8080.

Za funkcionalnosti koje zahtjevaju slanje poruke putem maila korištene su sopstvene mail adrese radi lakšeg testiranja, dakle nismo vadili vrijednost iz atributa korisnika. Na primjer:
	- za aktivaciju profila pacijenta(linija 161, fajl UserService)
	- za slanje potvrde za rezervaciju predefinisanog pregleda(linija 153 ,fajl AppointmentService)
	- za slanje zahtjeva za pregled kojim smo došli pretraživanjem doktora(linija 318, fajl AppointmentService)

Ukoliko želite da testirate funkcionalnosti možete promjeniti navedeni mail stavljajući svoj u gore navedenim linijama fajlova.

Postoje 2 korisnika tipa pacijent u bazi, sa username-om slavengaric@gmail.com i locdog96@gmail.com i siframa "user".
Za sistemskog admina je username(mail) milosslaven96@gmail.com(koji je u application.properties namjesten da bude uvijek posiljalac poruke za funkcionalnosti), a sifra "admin".