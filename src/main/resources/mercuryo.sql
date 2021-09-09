CREATE TABLE `MercuryoOrderRawDataa` (
                                        `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                        `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `transaction_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `widget_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `merchant_transaction_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `fiat_currency` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `fiat_amount` decimal(20,10) NOT NULL,
                                        `currency` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `amount` decimal(20,10) ,
                                        `status` varchar(32) COLLATE utf8mb4_unicode_ci ,
                                        `created_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                                        `updated_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                                        PRIMARY KEY (`order_id`),
                                        KEY `idx_fiat` (`fiat_currency`),
                                        KEY `idx_coin` (`currency`),
                                        KEY `idx_date` (`created_at`),
                                        KEY `index_completed_at` (`updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=4671 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;