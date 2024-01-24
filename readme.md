# SimpleERP

Bu proje basit anlamda bir Product(Ürün) tanımlamayı ve bu ürün veya ürünleri kullanarak Order(Sipariş) oluşturmayı
sağlayan bir backend projesidir.

## Başlangıç

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları takip edin.

### Gereksinimler

- Java 17
- Maven
- MySQL 8.0.33 ve daha üzeri.

### Kurulum

- Projeyi localinize alıp çalıştırmak için:
```bash
$ git clone https://github.com/kullanici/proje.git
$ cd SimpleERP
$ mvn clean install
```

Eğer projedeki veritabanı ayarları bilgisayarınız için uygun değilse application.yml dosyasını değiştirebilir
ya da proje config dosyasını uygun parametreler ile overload edebilirsiniz.

## Kullanılan Teknolojiler

- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- Spring Boot DevTools
- MySQL 8.0.33
- LOMBOK
- Spring Boot Test
- JUnit 5
- Mockito
- Spring Boot Validation
- Liquibase
- JavaFaker 1.0.2




