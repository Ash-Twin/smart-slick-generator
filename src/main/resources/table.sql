create table MercuryoTransactionData
(
    id                      varchar(64)                               not null,
    eventId                 varchar(64)                               not null,
    card_number             varchar(64)                               not null,
    type                    varchar(32)                               not null,
    user_uuid4              varchar(64)                               null,
    user_country_code       varchar(32)                               null,
    merchant_transaction_id varchar(64)                               not null,
    status                  varchar(32)                               not null,
    amount                  decimal(20, 10)                           not null,
    currency                varchar(32)                               not null,
    fiat_amount             decimal(20, 10)                           not null,
    fiat_currency           varchar(32)                               not null,
    created_at              timestamp(3) default CURRENT_TIMESTAMP(3) not null,
    updated_at              timestamp(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3),
    constraint id
        unique (id)
);

alter table MercuryoTransactionData
    add primary key (id);