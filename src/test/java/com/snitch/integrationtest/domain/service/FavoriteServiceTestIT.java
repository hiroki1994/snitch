package com.snitch.integrationtest.domain.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.snitch.domain.model.favorite.Favorite;
import com.snitch.domain.service.FavoriteService;

@SpringBootTest
@Transactional
public class FavoriteServiceTestIT {

    @Autowired
    FavoriteService favoriteService;

    @Test
    public void createOneFavorite_success() throws Exception {

	String userName = "userName3";
	int giftId = 1004;

	int expected = 1;
	int actual = favoriteService.createOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void createOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	String userName = "userName3";
	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createOneFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1000;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledUser() throws Exception {

	String userName = "disabledUser";
	int giftId = 1030;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledGift() throws Exception {

	String userName = "userName6";
	int giftId = 1031;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledRecommeder() throws Exception {

	String userName = "userName6";
	int giftId = 1032;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledUser_disabledGift() throws Exception {

	String userName = "disabledUser";
	int giftId = 1031;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledGift_disabledRecommeder() throws Exception {

	String userName = "userName6";
	int giftId = 1033;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledUser_disabledRecommeder() throws Exception {

	String userName = "disabledUser";
	int giftId = 1032;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createFavorite_fail_disabledUser_disabledGift_disabledRecommeder() throws Exception {

	String userName = "disabledUser";
	int giftId = 1033;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }


    @Test
    public void deleteOneFavorite_success() throws Exception {

	String userName = "userName3";
	int giftId = 1001;

	int expected = 1;
	int actual = favoriteService.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_giftIsNotAddedToFavorite() throws Exception {

	String userName = "userName3";
	int giftId = 1002;

	int expected = 0;
	int actual = favoriteService.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	String userName = "userName3";
	int giftId = 9999;

	int expected = 0;
	int actual = favoriteService.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1000;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledUser() throws Exception {

	String userName = "disabledUser";
	int giftId = 1000;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledGift() throws Exception {

	String userName = "username6";
	int giftId = 1031;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledRecommender() throws Exception {

	String userName = "username6";
	int giftId = 1031;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledUser_disabledGift() throws Exception {

	String userName = "disabledUser";
	int giftId = 1031;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledGift_disabledRecommender() throws Exception {

	String userName = "username6";
	int giftId = 1033;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledUser_disabledRecommender() throws Exception {

	String userName = "disabledUser";
	int giftId = 1032;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteFavorite_fail_disabledUser_disabledGift_disabledRecommender() throws Exception {

	String userName = "disabledUser";
	int giftId = 1033;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void countFavorite_success() throws Exception {

	String userName = "userName3";

	int expected = 2;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_success_noFavorite() throws Exception {

	String userName = "userName6";

	int expected = 0;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.count(userName);
	});
    }

    @Test
    public void countFavorite_zero_disabledUser() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_zero_disabledGift() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_zero_disabledRecommender() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_fail_disabledUser_disabledGift() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.count(userName);
	});
    }

    @Test
    public void countFavorite_zero_disabledGift_disabledRecommender() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_fail_disabledUser_disabledRecommender() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.count(userName);
	});
    }

    @Test
    public void countFavorite_fail_disabledUser_disabledGift_disabledRecommender() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.count(userName);
	});
    }

    @Test
    public void searchFavoriteId_success_found() throws Exception {

	String userName = "userName3";
	int giftId = 1000;

	boolean expected = true;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_success_notFound() throws Exception {

	String userName = "userName3";
	int giftId = 1002;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1002;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledUser() throws Exception {

	String userName = "disabledUser";
	int giftId = 1000;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledGift() throws Exception {

	String userName = "userName4";

	int giftId = 1031;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledRecommender() throws Exception {

	String userName = "userName4";

	int giftId = 1032;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledUser_disabledGift() throws Exception {

	String userName = "disabledUser";

	int giftId = 1031;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledGift_disabledRecommender() throws Exception {

	String userName = "userName4";

	int giftId = 1033;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledUser_disabledRecommender() throws Exception {

	String userName = "disabledUser";

	int giftId = 1032;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_disabledUser_disabledGift_disabledRecommender() throws Exception {

	String userName = "disabledUser";

	int giftId = 1033;

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void selectAllFavorites_success() throws Exception {

	String userName = "userName3";

	List<Favorite> favorites = favoriteService.selectAll(userName);

	assertThat(favorites, hasItems(hasProperty("favoriteId", is(1))));
	assertThat(favorites, hasItems(hasProperty("userId", is(1))));
	assertThat(favorites, hasItems(hasProperty("giftId", is(1000))));
	assertThat(favorites, hasItems(hasProperty("recommenderName", is("中越典子"))));
	assertThat(favorites, hasItems(hasProperty("giftName", is("マカロン"))));
	assertThat(favorites, hasItems(hasProperty("price", is("120個入　3938円"))));
	assertThat(favorites, hasItems(hasProperty("image", is("1000.jpg"))));
	assertThat(favorites, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
	assertThat(favorites, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
	assertThat(favorites, hasItems(hasProperty("phone", is("03-3352-1111"))));
    }

    @Test
    public void selectAllFavorites_success_noFavorite() throws Exception {

	String userName = "userName4";

	List<Favorite> favorites = favoriteService.selectAll(userName);

	assertThat(favorites, is(empty()));
    }

    @Test
    public void selectAllFavorites_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.selectAll(userName);
	});
    }

    @Test
    public void selectAllFavorites_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.selectAll(userName);
	});
    }

    @Test
    public void selectAllFavorites_fail_disabledGift() throws Exception {

	String userName = "userName4";

	List<Favorite> allFavorites = favoriteService.selectAll(userName);

	assertThat(allFavorites, is(empty()));
    }

    @Test
    public void selectAllFavorites_fail_disabledRecommeder() throws Exception {

	String userName = "userName4";

	List<Favorite> allFavorites = favoriteService.selectAll(userName);

	assertThat(allFavorites, is(empty()));
    }

    @Test
    public void selectAllFavorites_fail_disabledUser_disabledGift() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.selectAll(userName);
	});
    }

    @Test
    public void selectAllFavorites_fail_disabledGift_disabledRecommeder() throws Exception {

	String userName = "userName4";

	List<Favorite> allFavorites = favoriteService.selectAll(userName);

	assertThat(allFavorites, is(empty()));
    }

    @Test
    public void selectAllFavorites_fail_disabledUser_disabledRecommeder() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.selectAll(userName);
	});
    }

    @Test
    public void selectAllFavorites_fail_disabledUser_disabledGift_disabledRecommeder() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.selectAll(userName);
	});
    }


    @Test
    public void deleteAllFavorites_success() throws Exception {

	String userName = "userName3";

	int expected = 2;
	int actual = favoriteService.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorites_success_addNoFavorite() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorites_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteMany(userName);
	});
    }

    @Test
    public void deleteAllFavorite_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteMany(userName);
	});
    }

    @Test
    public void deleteAllFavorite_fail_disabledGift() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorite_fail_disabledRecommeder() throws Exception {

	String userName = "userName4";

	int expected = 0;
	int actual = favoriteService.deleteMany(userName);

	assertEquals(expected, actual);
    }
}