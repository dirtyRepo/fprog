-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 23, 2016 at 08:54 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ad_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `c_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `c_name`) VALUES
(12, '2'),
(8, 'asdasd'),
(6, 'building'),
(5, 'cars'),
(9, 'dsadasd'),
(1, 'dsadasdkkkkkkkkkk'),
(4, 'food'),
(10, 'hnghjgjhgjh'),
(2, 'home'),
(11, 'jhgjhjg'),
(7, 'lolol'),
(3, 'office');

-- --------------------------------------------------------

--
-- Table structure for table `favorite_category`
--

CREATE TABLE `favorite_category` (
  `user_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `favorite_category`
--

INSERT INTO `favorite_category` (`user_id`, `category_id`) VALUES
(1, 1),
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `favorite_post`
--

CREATE TABLE `favorite_post` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `feadback`
--

CREATE TABLE `feadback` (
  `feadback_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `body` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feadback`
--

INSERT INTO `feadback` (`feadback_id`, `post_id`, `user_id`, `created_at`, `body`) VALUES
(1, 1, 3, '2016-11-10', 'lol lol'),
(2, 1, 2, '2016-11-09', 'azuz azazaza'),
(3, 1, 2, '2016-11-16', 'kek'),
(4, 1, 3, '2016-11-12', 'shlabudabuda'),
(5, 3, 2, '2012-12-12', 'qweqwe'),
(8, 1, 1, '2012-12-12', 'hgfghf');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'new'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`post_id`, `user_id`, `category_id`, `title`, `description`, `created_at`, `updated_at`, `status`) VALUES
(1, 1, 1, 'post1', 'khdfjsgadhfh adsfjasdgfhjgasdhf sjhfghasd fjhsadgfygeuegfsdhjgf sdjhfghjsagf sjhfdg sdfjhgsfhgsdhfghasgf ', '2016-11-12', '2016-11-13', 'new'),
(2, 1, 6, 'asdfsdfg', 'dsfefuweiou886437865764756783645876783468', '2016-11-01', '2016-11-02', 'new'),
(3, 2, 2, 'title title title', 'deicrkshdjfh dfsakjasdf as7sdaf sadf7f 34 43 43 4 4 4 4 43 43 4 43 43 43 43 43 43', '2016-11-03', '2016-11-03', 'new'),
(4, 2, 3, 'tttr', 'wertwerxecfer', '2016-11-08', '2016-11-08', 'new'),
(5, 1, 1, 'new post', 'new', '2012-12-12', '2012-12-12', 'new');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `login`, `password`, `name`, `last_name`, `birth_date`, `email`, `address`, `phone`, `is_admin`) VALUES
(1, 'max_max ugh', '12345', 'max', 'drob', '05/03/95', 'drobmax@mail.ru', 'minsk', '+375445829192', 0),
(2, 'igorsheyko', '1234', 'lololoo', 'sadasd', 'asdasd', 'asdfasfds', 'sadfasdf', 'asdasd', 0),
(3, 'user1', 'password1', 'name1', 'ln1', '12/13/2001', 'user1@mail.ru', 'asdfgjh', '7566567368263748', 0),
(6, 'uytyytu', 'kjhjhhjk', 'kjjhghj', 'kjhjhkjkh', 'jbkjhkhjk', 'jhkjkhhjk', 'jhkkhjhjk', 'hjkjhkkhj', 0),
(7, 'sdfsda', 'dgdfgdsf', 'sdfgsdf', 'sfdgdsfg', 'dsfgsdf', 'sfgdfg', 'sdfg', 'dfgsd', 0),
(8, 'jhgjhg', 'jgjhg', 'jmghg', 'jhghj', 'jhghjg', 'kjhjgh', 'jghj', 'jghghj', 0),
(9, 'max', 'qwewq', 'wqrwqe', 'eqwwe', 'qwewqe', 'weqweqwe', 'weqweqwe', 'eqweqweqwe', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `c_name` (`c_name`);

--
-- Indexes for table `favorite_category`
--
ALTER TABLE `favorite_category`
  ADD UNIQUE KEY `user_id_2` (`user_id`,`category_id`),
  ADD KEY `user_id` (`user_id`,`category_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `favorite_post`
--
ALTER TABLE `favorite_post`
  ADD UNIQUE KEY `user_id_2` (`user_id`,`post_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `feadback`
--
ALTER TABLE `feadback`
  ADD PRIMARY KEY (`feadback_id`) USING BTREE,
  ADD UNIQUE KEY `feadback_id` (`feadback_id`,`post_id`,`user_id`),
  ADD KEY `post_id` (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`post_id`) USING BTREE,
  ADD KEY `category_id` (`category_id`),
  ADD KEY `category_id_2` (`category_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`) USING BTREE,
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `feadback`
--
ALTER TABLE `feadback`
  MODIFY `feadback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorite_category`
--
ALTER TABLE `favorite_category`
  ADD CONSTRAINT `favorite_category_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `favorite_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `favorite_post`
--
ALTER TABLE `favorite_post`
  ADD CONSTRAINT `favorite_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `favorite_post_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

--
-- Constraints for table `feadback`
--
ALTER TABLE `feadback`
  ADD CONSTRAINT `feadback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `feadback_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `posts_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
