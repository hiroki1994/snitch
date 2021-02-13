INSERT INTO userData(userId, userName, mailAddress, password, role) VALUES('2', 'userName3', 'mailaddress3@gmail.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'ROLE_GENERAL');

INSERT INTO guest(guestId, guestName) VALUES('1066', '中越典子');

INSERT INTO gift(giftId, guestId, giftName, price, image, description, shop, address, phone) VALUES('1066', '1066', 'マカロン', '120個入　3938円', '1066.jpg', 'ショコラアメール/ショコラヴァニーユ/ショコラフランボワーズショコラキャラメル/ショコラカフェ/ショコラミエルショコラピスターシュ/ショコラベルガモット/ショコラパッションポムアニス/マングコリアンドル/パンオレザン上記、12種類', 'ジャン＝ポール･エヴァン伊勢丹新宿店', '東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階', '03-3352-1111');

INSERT INTO favGift(userId, giftId) VALUES('2', '1066');
