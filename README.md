# tutorial-1

## Reflection 3
#### 1. Prinsip SOLID yang diaplikasikan
- Single Responsibility Principle. Dalam tutorial kali ini, saya memisahkan kode CarController dan HomeController dari
ProductController karena ketiga controller tersebut memiliki tujuan berbeda. Saya juga menghilangkan extend ProductController
pada CarController karena tidak diperlukan dan memiliki tujuan berbeda juga.
- Open Close Principle. Method-method yang terdapat pada Car dan Product dapat dipakai untuk subclass-subclass nya,
seperti edit, findById, create, dan delete
- Interface Segregation. CarService dan ProductService dibuat terpisah karena object yang mengimplementasikannya hanya 
mengimplementasikan salah satu saja, bukan keduanya.
- Dependancy Inversion. Prinsip ini dilakukan pada CarController dengan mengganti tipe data CarServiceImpl menjadi CarService
agar perubahan yang dilakukan di CarServiceImpl tidak merusak CarController.

#### 2. Advantages of applying SOLID principles
- Enhanced Maintainability. Penerapan SOLID principles membuat software lebih mudah untuk di-_maintain_ karena adanya 
_separations of concern_, yang membuat tiap komponen menjadi lebih simpel dan mudah untuk dimengerti
- Improved Testability. Software yang menerapkan SOLID principles akan menjadi lebih mudah untuk di test karena lebih sedikit
_dependency_ dan juga interface yang lebih jelas.
- Better Code Reusability. Komponen yang mengikuti SOLID principles akan dapat digunakan oleh komponen lain sehingga mengurangi
terjadinya _redundant code_

Beberapa contoh dari manfaat penerapan SOLID principles:
- SRP. Misal terdapat class `UserManager` yang bertugas untuk menangani management data dan notifikasi user. Dengan menerapkan SRP,
UserManager dapat dipisah menjadi `UserDataManager` dan `UserEmailNotifier` agar masing-masing kelas memiliki 1 tujuan saja.
- OCP. Misal terdapat class `ReportGenerator` yang awalnya untuk menghasilkan laporan HTML. Untuk menambahkan object PDF 
tanpa mengubah kelas yang ada, ReportGenerator dapat diperluas dengan subclass `PDFReportGenerator` yang mengimplementasikan object PDF.
- ISP. Misal terdapat interface `MultiFunctionDevice` yang memiliki metode untuk mencetak, meng-_scan_, dan mengirim teks. 
Tidak semua perangkat membutuhkan semua kemampuan ini. Dengan menerapkan ISP, interface dapat dibagi menjadi interface yang 
lebih kecil: `Printer`, `Scanner`, dan `Text` sehingga perangkat hanya mengimplementasikan interface yang sesuai dengan fungsionalitasnya.

#### Disadvantages of not applying SOLID principles
- Increased Complexity. Tanpa SOLID Principles, berarti kita tidak menerapkan prinsip yang pertama, yaitu Single Responsibility
Principles. Tanpa prinsip ini, kelas kita akan berusaha untuk menghandle banyak _responsibility_, yang menyebabkan kode menjadi
kompleks dan sulit dibaca
- Difficulties in Maintenance and Extension


## Reflection 2
#### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them:

Ketika pertama kali menjalankan code scanner, PMD mendeteksi ada beberapa code issues.
Code issues yang terdeteksi oleh PMD meliputi:
Redundant parentheses, Unused import, Missed branch. Saya memperbaiki kodenya
dengan mengikuti instruksi dari hasil deteksi code scanner
dan memperbaiki issues yang ada hingga hanya menyisakan satu issue yang tersisa.

#### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Menurut saya, CI/CD yang saya implementasikan sudah memenuhi definisi dari CI/CD Deployment.
Ketika saya mem-push ke branch master, maka Continuous Integration akan dijalankan sesuai kode yang ada pada ci.yml, 
serta code scanner yang ada (scorecard dan PMD) juga akan meng-scan kode saya
untuk mengecek quality issues yang ada. Dan untuk Continuous Development, akan dijalankan
oleh PaaS Koyeb secara otomatis, sehingga sudah memenuhi definisi dari CI/CD development.


## Reflection 1
Pada tutorial pertama dan di pekan pertama ini,
saya telah mempelajari clean code, git workflow,
secure code, dan unit testing. Di tutorial ini,
saya telah menerapkan unsur-unsur dari clean code,
seperti memberi nama variabel yang bermakna dalam kode saya
(Meaningful Names) dan tidak memberi komentar yang tidak penting
dalam kode saya.

Kesalahan yang saya temukan dalam kode saya adalah kecerobohan dalam
workflow, di mana saya tidak membuat edit branch dan delete branch
dalam waktu yang bersamaan, sehingga menghasilkan workflow yang kurang
efisien. Kedepannya, saya akan mencegah kesalahan tersebut terjadi lagi
dengan teliti dan berhati-hati dalam melakukan branching.

## Reflection 2

* How many unit tests should be made in a class?

Menurut saya, tidak ada ketentuan pasti dalam jumlah tests yang harus dimiliki
oleh suatu kelas. Yang penting adalah test-test tersebut telah mencakup
semua fungsi dan logika yang ada pada kode.

* How to make sure that our unit tests are enough to verify our program?

Unit tests dikatakan cukup untuk memverifikasi program yang ada apabila
sudah mencakup _expected behavior, potential errors, boundary errors,_ dan _edge cases_.

* If you have 100% code coverage, does that mean your code has no bugs or errors?

100% code coverage menandakan bahwa tiap line dalam kode telah dijalankan
setidaknya satu kali. Hal ini meminimalisir terjadinya bug dan error, namun
tidak menjamin bahwa bug dan error tidak akan muncul sama sekali dalam kode. Misalnya
masih terdapatnya logical error dalam kode.

* What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality?

Menurut saya _cleanliness_ dari kode tersebut akan berkurang
karena terdapatnya pengulangan dalam program yang menentang prinsip
dari _clean code_ itu sendiri.


