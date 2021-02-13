INSERT INTO userData(userName, mailAddress, password, role) VALUES('userName3', 'mailaddress3@gmail.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'ROLE_GENERAL');

INSERT INTO guest(guestId, guestName) VALUES('1000', '中越典子');
INSERT INTO guest(guestId, guestName) VALUES('1001', 'ミムラ');

INSERT INTO gift(giftId, guestId, giftName, price, image, description, shop, address, phone) VALUES('1000', '1000', 'マカロン', '120個入　3938円', '1000.jpg', 'ショコラアメール/ショコラヴァニーユ/ショコラフランボワーズショコラキャラメル/ショコラカフェ/ショコラミエルショコラピスターシュ/ショコラベルガモット/ショコラパッションポムアニス/マングコリアンドル/パンオレザン上記、12種類', 'ジャン＝ポール･エヴァン伊勢丹新宿店', '東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階', '03-3352-1111');
INSERT INTO gift(giftId, guestId, giftName, price, image, description, shop, address, phone) VALUES('1001', '1001', '五家寶', '細巻6本入り 170円', '1001.jpg', '各種詰め合わせあります。ぷうん、と香るきな粉の香ばしさ、サクッとした歯応え、そしてすうっと溶け入る舌触り・・・・・・。主原料である大豆、糯米、砂糖、水飴等が織りなすこの豊かな風味は、総て独自のものを用い、心のこもった手造りによる『花堤五家寳』ならではのものです。', '花堤', '玉県熊谷市榎町3', '048-521-0471');

INSERT INTO favGift(userId, giftId) VALUES('1', '1000');
INSERT INTO favGift(userId, giftId) VALUES('1', '1001');
