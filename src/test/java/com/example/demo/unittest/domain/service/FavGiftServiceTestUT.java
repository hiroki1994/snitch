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

import com.example.demo.domain.model.favorite.FavGift;
import com.example.demo.domain.repository.FavGiftDao;
import com.example.demo.domain.service.FavGiftService;



@SpringBootTest
@Transactional
public class FavGiftServiceTestUT {


	@Mock
	FavGiftDao favGiftDao;

	@InjectMocks
	FavGiftService favGiftService;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void createFavGift_success() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		when(favGiftDao.create(userName, giftId)).thenReturn(1);

		int expected = 1;
		int actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void createFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		when(favGiftDao.create(userName, giftId)).thenThrow(DataIntegrityViolationException.class);

		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledUser() throws Exception {

		String userName = "disabledUser";
		int giftId = 1030;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledGift() throws Exception {

		String userName = "userName6";
		int giftId = 1031;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledRecommeder() throws Exception {

		String userName = "userName6";
		int giftId = 1032;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledUser_disabledGift() throws Exception {

		String userName = "disabledUser";
		int giftId = 1031;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledGift_disabledRecommeder() throws Exception {

		String userName = "userName6";
		int giftId = 1033;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledUser_disabledRecommeder() throws Exception {

		String userName = "disabledUser";
		int giftId = 1032;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_disabledUser_disabledGift_disabledRecommeder() throws Exception {

		String userName = "disabledUser";
		int giftId = 1033;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.create(userName, giftId);
		});
	}

	@Test
	public void deleteFavGift_success() throws Exception {

		String userName = "userName3";
		int giftId = 1001;

		when(favGiftDao.delete(userName, giftId)).thenReturn(1);

		int expected = 1;
		int actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift_fail_giftIsNotAddedToFavGift() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		when(favGiftDao.delete(userName, giftId)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		when(favGiftDao.delete(userName, giftId)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		when(favGiftDao.delete(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.delete(userName, giftId);
		});
	}

	@Test
	public void deleteFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.delete(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.delete(userName, giftId);
		});
	}

	@Test
	public void countFavGift_success() throws Exception {

		String userName = "userName3";

		when(favGiftDao.count(userName)).thenReturn(2);

		int expected = 2;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_zero__NoFavGift() throws Exception {

		String userName = "userName6";

		when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(favGiftDao.count(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.count(userName);
		});
	}

	@Test
	public void countFavGift_zero_disabledUser() throws Exception {

		String userName = "userName4";

		when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_zero_disabledGift() throws Exception {

		String userName = "userName4";

		when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_zero_disabledRecommender() throws Exception {

	    	String userName = "userName4";

	    	when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_fail_disabledUser_disabledGift() throws Exception {

		String userName = "disabledUser";

		when(favGiftDao.count(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.count(userName);
		});
	}

	@Test
	public void countFavGift_zero_disabledGift_disabledRecommender() throws Exception {

	    	String userName = "userName4";

	    	when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_fail_disabledUser_disabledRecommender() throws Exception {

		String userName = "disabledUser";

		when(favGiftDao.count(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.count(userName);
		});
	}

	@Test
	public void countFavGift_fail_disabledUser_disabledGift_disabledRecommender() throws Exception {

		String userName = "disabledUser";

		when(favGiftDao.count(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftService.count(userName);
		});
	}

	@Test
	public void searchFavId_success_found() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		when(favGiftDao.existFavId(userName, giftId)).thenReturn(1);

		boolean expected = true;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_success_notFound() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1002;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledUser() throws Exception {

		String userName = "disabledUser";
		int giftId = 1000;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledGift()throws Exception {

		String userName = "userName4";

		int giftId = 1031;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledRecommender()throws Exception {

		String userName = "userName4";

		int giftId = 1032;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledUser_disabledGift()throws Exception {

		String userName = "disabledUser";

		int giftId = 1031;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledGift_disabledRecommender()throws Exception {

		String userName = "userName4";

		int giftId = 1033;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledUser_disabledRecommender()throws Exception {

		String userName = "disabledUser";

		int giftId = 1032;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_disabledUser_disabledGift_disabledRecommender()throws Exception {

		String userName = "disabledUser";

		int giftId = 1033;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}


	@Test
	public void listFavGift_success() throws Exception {

		String userName = "userName3";
		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftDao.selectAll(userName)).thenReturn(allFavGifts);

		List<FavGift> expected = allFavGifts;
		List<FavGift> actual = favGiftService.selectAll(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void listFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(favGiftDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.selectAll(userName);
		});
	}

	@Test
	public void deleteAllFavGift_success() throws Exception {

		String userName = "userName3";

		when(favGiftDao.deleteMany(userName)).thenReturn(2);

		int expected = 2;
		int actual = favGiftService.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void listFavGift_fail_disabledUser() throws Exception {

		String userName = "disabledUser";

		when(favGiftDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftDao.selectAll(userName);
		});
	}

	@Test
	public void listFavGift_fail_disabledGift() throws Exception {

		String userName = "userName4";

		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftDao.selectAll(userName)).thenReturn(allFavGifts);

		List<FavGift> expected = allFavGifts;
		List<FavGift> actual = favGiftService.selectAll(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void listFavGift_fail_disabledRecommeder() throws Exception {

		String userName = "userName4";

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		when(favGiftDao.selectAll(userName)).thenReturn(allFavGifts);

		List<FavGift> expected = allFavGifts;
		List<FavGift> actual = favGiftService.selectAll(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void listFavGift_fail_disabledUser_disabledGift() throws Exception {

		String userName = "disabledUser";

		when(favGiftDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftDao.selectAll(userName);
		});
	}

	@Test
	public void listFavGift_fail_disabledGift_disabledRecommeder() throws Exception {

		String userName = "userName4";

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		when(favGiftDao.selectAll(userName)).thenReturn(allFavGifts);

		List<FavGift> expected = allFavGifts;
		List<FavGift> actual = favGiftService.selectAll(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void listFavGift_fail_disabledUser_disabledRecommeder() throws Exception {

	    	String userName = "disabledUser";

		when(favGiftDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftDao.selectAll(userName);
		});
	}

	@Test
	public void listFavGift_fail_disabledUser_disabledGift_disabledRecommeder() throws Exception {

	    	String userName = "disabledUser";

		when(favGiftDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	favGiftDao.selectAll(userName);
		});
	}

	@Test
	public void deleteAllFavGift_success_addNoFav() throws Exception {

		String userName = "userName4";

		when(favGiftDao.deleteMany(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(favGiftDao.deleteMany(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.deleteMany(userName);
		});
	}
}
