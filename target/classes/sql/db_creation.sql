-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tax_data
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tax_data
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tax_data` DEFAULT CHARACTER SET cp1251 ;
USE `tax_data` ;

-- -----------------------------------------------------
-- Table `tax_data`.`entrepreneur_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tax_data`.`entrepreneur_type` ;

CREATE TABLE IF NOT EXISTS `tax_data`.`entrepreneur_type` (
                                                              `id` INT NOT NULL AUTO_INCREMENT,
                                                              `type` VARCHAR(25) CHARACTER SET 'cp1251' NOT NULL,
                                                              PRIMARY KEY (`id`),
                                                              UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                              UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_data`.`period`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tax_data`.`period` ;

CREATE TABLE IF NOT EXISTS `tax_data`.`period` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `period` VARCHAR(14) CHARACTER SET 'cp1251' NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                   UNIQUE INDEX `period_UNIQUE` (`period` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_data`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tax_data`.`status` ;

CREATE TABLE IF NOT EXISTS `tax_data`.`status` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `state` VARCHAR(15) CHARACTER SET 'cp1251' NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                   UNIQUE INDEX `state_UNIQUE` (`state` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_data`.`role_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tax_data`.`role_type` ;

CREATE TABLE IF NOT EXISTS `tax_data`.`role_type` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `role` VARCHAR(15) CHARACTER SET 'cp1251' NOT NULL,
                                                      PRIMARY KEY (`id`),
                                                      UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                      UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_data`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tax_data`.`user` ;

CREATE TABLE IF NOT EXISTS `tax_data`.`user` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `role_id` INT NOT NULL,
                                                 `entrepreneur_type_id` INT NULL,
                                                 `name` VARCHAR(16) CHARACTER SET 'cp1251' NOT NULL,
                                                 `lastname` VARCHAR(16) CHARACTER SET 'cp1251' NULL,
                                                 `email` VARCHAR(255) CHARACTER SET 'cp1251' NOT NULL,
                                                 `password` VARCHAR(16) CHARACTER SET 'cp1251' NOT NULL,
                                                 `registration_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                 `tin` INT(10) UNSIGNED ZEROFILL NOT NULL,
                                                 `address` VARCHAR(45) CHARACTER SET 'cp1251' NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                                                 UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                 INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
                                                 INDEX `fk_user_entrepreneur_type1_idx` (`entrepreneur_type_id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tax_data`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tax_data`.`report` ;

CREATE TABLE IF NOT EXISTS `tax_data`.`report` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `status_id` INT NOT NULL,
                                                   `period_id` INT NOT NULL,
                                                   `income` BIGINT UNSIGNED NOT NULL,
                                                   `tax_rate` INT UNSIGNED NOT NULL,
                                                   `comment` VARCHAR(100) CHARACTER SET 'cp1251' NULL DEFAULT NULL,
                                                   `last_change_date` TIMESTAMP NULL DEFAULT NULL,
                                                   `report_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                   `user_id` INT NOT NULL,
                                                   `year` YEAR NULL DEFAULT NULL,
                                                   PRIMARY KEY (`id`),
                                                   INDEX `fk_report_status1_idx` (`status_id` ASC) VISIBLE,
                                                   INDEX `fk_report_period1_idx` (`period_id` ASC) VISIBLE,
                                                   INDEX `fk_report_user1_idx` (`user_id` ASC) INVISIBLE,
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
