![Free_Sample_By_Wix_waifu2x_art_noise3_scale_tta_1.png](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/2aacbd0c-bbbc-5fc6-c802-0d5d8dc2ed79.png)

# Snitch

Snitchは**著名人御用達のお土産に特化したアプリケーション**です。

こちらのアプリケーションは、2018年3月に惜しくも放送終了した 「とんねるずのみなさんのおかげでした」の人気コーナー「食わず嫌い王」にて紹介されたお土産のみを掲載しております。 ​ 番組のファンだった方、どんなお土産を買おうか迷っている方、小腹を満たしたい方などあらゆる方に楽しんでいただきたく​当アプリケーションを開発しました。

## サイト（Herokuでアップデートしてからかな）

下記のサイトにて公開しています。


## 機能一覧
- ユーザー登録/ログイン機能
- コンテンツ検索機能
- コンテンツお気に入り機能
  - お気に入り登録
  - お気に入り解除
  - お気に入り一覧
- ユーザー登録情報更新
- 退会機能
- バリデーション機能
- コンテンツ詳細画面での地図掲載

## 使用技術
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

## 会員登録の方法

![resistration.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/893bc43f-6710-5a19-3151-106732f74b88.gif)



サイト上部にある「Login」をクリックすると、ログインページに移動します。
「新規登録」 ボタンを押すと登録フォームの入力を促されます。
登録が無事に完了すると、マイページに移動します。
マイページではお気に入りしたコンテンツを見たり、登録した情報を上書きしたり、退会処理ができます。

## 検索

![search.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/c6d3c28a-a343-be17-d728-ed14f67ea6b3.gif)

検索フォームでキーワードを入力すると、お土産を検索することができます。
検索フォームは画面上部中央にあります。

## お気に入り登録

![addfav.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/89996aa1-5f6f-af18-e187-040dbccca3e9.gif)

ログイン済みの場合、お気に入り機能が使えるようになります。
お土産の詳細画面にある「お気に入り」と書かれた黄色のボタンを押すと、お気に入りリストに追加することができます。
お気に入りしたお土産は、マイページの「お気に入り」で確認できます。

## お気に入り解除

![deletefav.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/18a0f445-38e3-4f23-abbe-35fc2bce0cec.gif)

お気に入りリストからお土産を外すには、お土産の詳細画面を開き「お気に入り解除」を押します。

## 登録情報変更

![updating.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/baee4065-aa06-c696-f4d2-31601849acf2.gif)

登録情報更新はマイページから行えます。
マイページにある「登録情報更新」と書かれたボタンを押すと、登録情報変更ページに遷移します。
フォームに新しいユーザーネーム、もしくは新しいメールアドレスを入力し、パスワードを入力します。
パスワードを変更しない場合は、現在使用中のものを入力してください。
「更新」と書かれたボタンを押すと、再度マイページに移行します。


## 退会

![withdrawal.gif](https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/639462/a06f8646-835f-f31f-a43c-5593873160a9.gif)

退会はマイページから行えます。
マイページにある「退会」と書かれたボタンを押すと、退会ページに遷移します。
「退会」と書かれたボタンを押すと、退会処理が行われます。


## 作者
佐藤博紀(https://qiita.com/hiroki1994)