SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `healthfulu` ;
CREATE SCHEMA IF NOT EXISTS `healthfulu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `healthfulu` ;

-- -----------------------------------------------------
-- Table `healthfulu`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healthfulu`.`user` ;

CREATE TABLE IF NOT EXISTS `healthfulu`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(255) NULL,
  `log_in` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `healthfulu`.`data_source`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `healthfulu`.`data_source` ;

CREATE TABLE IF NOT EXISTS `healthfulu`.`data_source` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(256) NULL,
  `oauth_token` VARCHAR(256) NULL,
  `oauth_token_secret` VARCHAR(256) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_data_source_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_data_source_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `healthfulu`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
