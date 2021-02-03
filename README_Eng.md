![Free_Sample_By_Wix_waifu2x_art_noise3_scale_tta_1.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/2aacbd0c-bbbc-5fc6-c802-0d5d8dc2ed79.png)

# Snitch

**Snitch is an application that specializes in gifts which celebs like.**

This app posts mainly gifts which was featured in a popular segment “ KuwazuGiraiOu” of Japanese TV program  “Tonneruzu no Minasan no Okage deshita”   which ended in March 2018.  This application was made for fans of the TV program, everyone who is looking for a gift or is a little bit hungry.

## Access Snitch

This application can be used on a below site.

## function

- Resitration/Authentication
- Searching Content
- Adding to favorites
  - Add a favorite gift to a list
  - Remove a gift from a list of favorite gift
  - Showing a list of favorite contents

- Updating the registered information
- Withdraw
- Validation
- Adding a Google Map with a Marker in a detail page of contents.

## Built With

- Java SE 14.0.1
- spring-boot 2.3.3
- jquery 3.3.1-1
- h2 1.4.200(ローカル環境)
- postgresql.version 42.2.14(herokuデプロイ時)
- bootstrap 4.2.1
- thymeleaf
- Google Maps API
- spring-security.version　5.3.4.
- lombok.version 1.18.12
- thymeleaf-layout-dialect.version 2.4.1

## Resitration

![signupEG.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/0a274ce7-bba6-81ee-5a5e-074177b2a070.gif)


Click on a link “Login” in the top of a  screen.
Click a green button “sign up”  and type a user name ,an e-mail address and a password.
When the registration is finished, move to a user page,”Mypage”.
In the user page, a list of favorite contents , updating the registered information and a withdraw processing is available.

## Search Gifts

![searchEG.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/b51caa93-3905-0e72-1a63-d47e9d90f4e8.gif)

You can search gifts by entering keyword (e.g “Shibuya”, “Tanaka”,”chocolate”).
A search form exists in the upper middle.

## Add a favorite gift to a list of favorite contents

![addfavEG.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/9d0488b2-b466-720f-d32b-5ef2e89205a5.gif)

Logging in the site, you can add a favorite gift to a list of favorite contents.

In the detailed page of a gift, there is a yellow button ,” favorite” , to add a favorite gift to the list .
Pushing the button, the button changes into a red button “Unfavorite”.
A list of favorite gifts can be checked by Accessing a link named “favorite” in MyPage.

## Remove a gift from a list of favorite gifts

![deletefavEG.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/51843188-5b4f-a3a3-5768-c1aad392c636.gif)


Pushing the red button,”Unfavorite”,removes a gift from a list of favorites gifts.

## Update the registered information

![updatingEG.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/f3d2e3de-0582-b7ee-eb8a-50396a1b316c.gif)


 Updating the registered information is available in the “Mypage”.
Pushing the button,“Registration infomation”,transits to the page for Updating the registered information.
Type a new user name ,an e-mail address and a password.
If you do not want to change a password, type a password you use to log in.
Pushing the button,“update”, transits to “Mypage” again.

## Withdraw

![withdrawalEG.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/a9628ef4-0e56-6f91-1070-3074132db350.gif)

Withdrawing is available in the “Mypage”.
Pushing the button, “Withdrawal”,transits to the page for withdrawing.
Withdraw processing will be executed by pushing the button, “Withdrawal”.



## Author
Hiroki Sato(https://qiita.com/hiroki1994)


