
-- Status Card Cache
CREATE TABLE `card_cache` (
    `id` INT NOT NULL AUTO_INCREMENT ,
    `card_number` VARCHAR(50) NOT NULL ,
    `scheme` VARCHAR(200) NOT NULL ,
    `type` VARCHAR(200) NOT NULL ,
    `bank` VARCHAR(500) NOT NULL ,
    PRIMARY KEY (`id`),
    UNIQUE (`card_number`)
);

-- Status Hit Stat
CREATE TABLE `stat` (
    `id` INT NOT NULL AUTO_INCREMENT ,
    `card_number` VARCHAR(50) NOT NULL ,
    `hit_counter` INT NOT NULL ,
    PRIMARY KEY (`id`),
    UNIQUE (`card_number`)
);
