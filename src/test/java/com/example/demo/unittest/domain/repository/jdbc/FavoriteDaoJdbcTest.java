package com.example.demo.unittest.domain.repository.jdbc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.favorite.Favorite;
import com.example.demo.domain.repository.jdbc.FavoriteDaoJdbcImpl;

@SpringBootTest
@Transactional
public class FavoriteDaoJdbcTest {

    @Autowired
    FavoriteDaoJdbcImpl favoriteDaoJdbcImpl;

    @Test
    public void countFavorite_success() throws Exception {

	String userName = "userName3";

	int expected = 2;
	int actual = favoriteDaoJdbcImpl.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_noFavorite() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteDaoJdbcImpl.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.count(userName);
	});
    }

    @Test
    public void selectAllFavorites_success() throws Exception {

	String userName = "userName3";

	List<Favorite> favorite = favoriteDaoJdbcImpl.selectAll(userName);

	assertThat(favorite, hasItems(hasProperty("favId", is(0))));
	assertThat(favorite, hasItems(hasProperty("userId", is(1))));
	assertThat(favorite, hasItems(hasProperty("giftId", is(1000))));
	assertThat(favorite, hasItems(hasProperty("guestName", is("中越典子"))));
	assertThat(favorite, hasItems(hasProperty("giftName", is("マカロン"))));
	assertThat(favorite, hasItems(hasProperty("price", is("120個入　3938円"))));
	assertThat(favorite, hasItems(hasProperty("image", is("1000.jpg"))));
	assertThat(favorite, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
	assertThat(favorite, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
	assertThat(favorite, hasItems(hasProperty("phone", is("03-3352-1111"))));
    }

    @Test
    public void selectAllFavorites_success_noFavorite() throws Exception {

	String userName = "userName4";

	List<Favorite> favorite = favoriteDaoJdbcImpl.selectAll(userName);

	assertThat(favorite, is(empty()));
    }

    @Test
    public void selectAllFavorites_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.selectAll(userName);
	});
    }

    @Test
    public void createOneFavorite_success() throws Exception {

	String userName = "userName3";
	int giftId = 1004;

	int expected = 1;
	int actual = favoriteDaoJdbcImpl.createOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void createOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	String userName = "userName3";
	int giftId = 9999;

	Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
	    favoriteDaoJdbcImpl.createOne(userName, giftId);
	});
    }

    @Test
    public void createOneFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1000;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.createOne(userName, giftId);
	});
    }

    @Test
    public void createOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.createOne(userName, giftId);
	});
    }

    @Test
    public void deleteOneFavorite_success() throws Exception {

	String userName = "userName3";
	int giftId = 1000;

	int expected = 1;
	int actual = favoriteDaoJdbcImpl.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_giftIsNotAddedToFavorite() throws Exception {

	String userName = "userName3";
	int giftId = 1002;

	int expected = 0;
	int actual = favoriteDaoJdbcImpl.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	String userName = "userName3";
	int giftId = 9999;

	int expected = 0;
	int actual = favoriteDaoJdbcImpl.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1000;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.deleteOne(userName, giftId);
	});
    }

    @Test
    public void searchFavorite_success_found() throws Exception {

	String userName = "userName3";

	int giftId = 1001;

	int expected = 2;
	int actual = favoriteDaoJdbcImpl.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavorite_success_notFound() throws Exception {

	String userName = "userName3";
	int giftId = 1002;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.exist(userName, giftId);
	});
    }

    @Test
    public void searchFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1002;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.exist(userName, giftId);
	});
    }

    @Test
    public void searchFavoriteId_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.exist(userName, giftId);
	});
    }

    @Test
    public void deleteAllFavorites_success() throws Exception {

	String userName = "userName3";

	int expected = 2;
	int actual = favoriteDaoJdbcImpl.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorites_success_addNoFavorite() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteDaoJdbcImpl.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorites_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteDaoJdbcImpl.deleteMany(userName);
	});
    }
}