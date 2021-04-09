package com.example.demo.unittest.domain.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.favorite.Favorite;
import com.example.demo.domain.repository.FavoriteDao;
import com.example.demo.domain.service.FavoriteService;

@SpringBootTest
@Transactional
public class FavoriteServiceTestUT {

    @Mock
    FavoriteDao favoriteDao;

    @InjectMocks
    FavoriteService favoriteService;

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOneFavorite_success() throws Exception {

	String userName = "userName3";
	int giftId = 1000;

	when(favoriteDao.createOne(userName, giftId)).thenReturn(1);

	int expected = 1;
	int actual = favoriteService.createOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void createOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	String userName = "userName3";
	int giftId = 9999;

	when(favoriteDao.createOne(userName, giftId)).thenThrow(DataIntegrityViolationException.class);

	Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createOneFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1000;

	when(favoriteDao.createOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void createOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	when(favoriteDao.createOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.createOne(userName, giftId);
	});
    }

    @Test
    public void deleteOneFavorite_success() throws Exception {

	String userName = "userName3";
	int giftId = 1001;

	when(favoriteDao.deleteOne(userName, giftId)).thenReturn(1);

	int expected = 1;
	int actual = favoriteService.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_giftIsNotAddedToFavorite() throws Exception {

	String userName = "userName3";
	int giftId = 1002;

	when(favoriteDao.deleteOne(userName, giftId)).thenReturn(0);

	int expected = 0;
	int actual = favoriteService.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	String userName = "userName3";
	int giftId = 9999;

	when(favoriteDao.deleteOne(userName, giftId)).thenReturn(0);

	int expected = 0;
	int actual = favoriteService.deleteOne(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1000;

	when(favoriteDao.deleteOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void deleteOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	when(favoriteDao.deleteOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteOne(userName, giftId);
	});
    }

    @Test
    public void countFavorite_success() throws Exception {

	String userName = "userName3";

	when(favoriteDao.count(userName)).thenReturn(2);

	int expected = 2;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_success_NoFavorite() throws Exception {

	String userName = "userName4";

	when(favoriteDao.count(userName)).thenReturn(0);

	int expected = 0;
	int actual = favoriteService.count(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void countFavorite_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	when(favoriteDao.count(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.count(userName);
	});
    }

    @Test
    public void searchFavoriteId_success_found() throws Exception {

	String userName = "userName3";
	int giftId = 1000;

	when(favoriteDao.exist(userName, giftId)).thenReturn(1);

	boolean expected = true;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_success_notFound() throws Exception {

	String userName = "userName3";
	int giftId = 1002;

	when(favoriteDao.exist(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 1002;

	when(favoriteDao.exist(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void searchFavoriteId_fail_userNameAndUserIdDoesNotExist() throws Exception {

	String userName = "userName5";
	int giftId = 9999;

	when(favoriteDao.exist(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

	boolean expected = false;
	boolean actual = favoriteService.exist(userName, giftId);

	assertEquals(expected, actual);
    }

    @Test
    public void selectAllFavorites_success() throws Exception {

	String userName = "userName3";
	List<Favorite> favorite = new ArrayList<>();

	when(favoriteDao.selectAll(userName)).thenReturn(favorite);

	List<Favorite> expected = favorite;
	List<Favorite> actual = favoriteService.selectAll(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void selectAllFavorites_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	when(favoriteDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.selectAll(userName);
	});
    }

    @Test
    public void deleteAllFavorites_success() throws Exception {

	String userName = "userName3";

	when(favoriteDao.deleteMany(userName)).thenReturn(2);

	int expected = 2;
	int actual = favoriteService.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorites_success_addNoFavorite() throws Exception {

	String userName = "userName4";

	when(favoriteDao.deleteMany(userName)).thenReturn(0);

	int expected = 0;
	int actual = favoriteService.deleteMany(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteAllFavorites_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	when(favoriteDao.deleteMany(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    favoriteService.deleteMany(userName);
	});
    }
}
