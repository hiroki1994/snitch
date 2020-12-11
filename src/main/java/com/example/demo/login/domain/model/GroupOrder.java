package com.example.demo.login.domain.model;

import javax.validation.GroupSequence;

//ユーザー新規登録とユーザー登録情報更新の際に「ValidGroup1」「ValidGroup2」の順番にバリデーションを実行
@GroupSequence({ValidGroup1.class, ValidGroup2.class})
public interface GroupOrder {


}
