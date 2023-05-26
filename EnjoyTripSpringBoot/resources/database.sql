-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema trip_together
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trip_together` DEFAULT CHARACTER SET utf8 ;
USE `trip_together` ;  
-- -----------------------------------------------------
-- Table `trip_together`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trip_together`.`user` (
  `id` VARCHAR(100) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `pwd` VARCHAR(100) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `manager` INT(11) NOT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `trip_together`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trip_together`.`plan` (
  `user_id` VARCHAR(100) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `start_date` TIMESTAMP NULL DEFAULT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `public` TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `user_id_to_plan_user_id_fk_idx` (`user_id` ASC) ,
  CONSTRAINT `user_id_to_plan_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `trip_together`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `trip_together`.`plan_detail`
-- -----------------------------------------------------
CREATE TABLE `plan_detail` (
  `plan_id` int(11) NOT NULL,
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  `route` int(11) NOT NULL,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `photoUrl` varchar(500) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `plan_id_to_plan_detail_plan_id_fk_idx` (`plan_id`),
  CONSTRAINT `plan_id_to_plan_detail_plan_id_fk` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `plan_detail` WRITE;
/*!40000 ALTER TABLE `plan_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_detail` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `trip_together`.`plan_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trip_together`.`plan_member` (
  `plan_id` INT NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `access` TINYINT NOT NULL,
  `confirm` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_to_plan_member_user_id_idx` (`user_id` ASC) ,
  INDEX `plan_id_to_plan_member_plan_id_idx` (`plan_id` ASC) ,
  CONSTRAINT `user_id_to_plan_member_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `trip_together`.`user` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `plan_id_to_plan_member_plan_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `trip_together`.`plan` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `trip_together`.`hotplace`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trip_together`.`hotplace` (
  `user_id` VARCHAR(100) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `visit_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `write_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_num` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_to_hotplace_user_id_fk_idx` (`user_id` ASC),
  CONSTRAINT `user_id_to_hotplace_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `trip_together`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `trip_together`.`hotplace_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trip_together`.`hotplace_file` (
  `hotplace_id` INT(11) NOT NULL,
  `fid` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `path` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`fid`),
  INDEX `hotplace_id_to_hotplace_file_hotplace_hotplace_id_fk_idx` (`hotplace_id` ASC),
  CONSTRAINT `hotplace_id_to_hotplace_file_hotplace_hotplace_id_fk`
    FOREIGN KEY (`hotplace_id`)
    REFERENCES `trip_together`.`hotplace` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `trip_together`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trip_together`.`notice` (
  `user_id` VARCHAR(100) NOT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT(11) NOT NULL DEFAULT '0',
  `write_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id_to_notice_user_id_fk_idx` (`user_id` ASC),
  CONSTRAINT `user_id_to_notice_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `trip_together`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
