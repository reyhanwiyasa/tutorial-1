# tutorial-1

## Reflection 3
#### 1. Prinsip SOLID yang diaplikasikan
- Single Responsibility Principle. Dalam tutorial kali ini, saya memisahkan kode 

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


