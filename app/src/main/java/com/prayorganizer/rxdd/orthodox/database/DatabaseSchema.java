package com.prayorganizer.rxdd.orthodox.database;

/**
 * Created by 22757 on 30.12.2017.
 *
 */

class DatabaseSchema {
    static final class Tables {

        static final String PRAYS_MAIN = "prays_main";
        static final String PRAYS_CATS = "prays_cats";
        static final String PRAYS_MASTER = "prays_cats_id_master";
        static final String PRAYS_SLAVE = "prays_cats_id_slave";
        static final String PRAYS_KONTAK = "prays_kontakion";
        static final String PRAYS_TROP = "prays_troparion";

        static final String PSALMS_MASTER = "psalt_cat";
        static final String PSALM_HEAD = "psalt_head";
        static final String PSALM_MAIN = "psalt_main";

    }
    static final class Columns {
        static final String PRAY_ID = "_pray_id";
        static final String PRAY_TEXT = "praytext";
        static final String PRAY_TITLE = "pray_title";
        static final String PRAY_FAV = "pray_fav";
        static final String PRAY_TAG = "pray_tags";
        static final String CAT_ID = "_cat_id";
        static final String PRAY_MASTER_ID = "_master_id";
        static final String PRAY_MASTER_IMAGE = "img_master";
        static final String PRAY_MASTER_TAG = "tag_master";
        static final String PRAY_MASTER_CATNAME = "cat_master";
        static final String PRAY_SLAVE_ID = "_slave_id";
        static final String PRAY_SLAVE_CATNAME = "cat_slave";
        static final String PRAY_SLAVE_IMAGE = "img_slave";

        static final String PSALM_CAT_ID = "_id_psalt_cat";
        static final String PSALM_KAF_ID = "_kaf_id";
        static final String PSALM_RU = "psalt_ru_line";
        static final String PSALM_CSL = "psalt_csl_line";
        static final String PSALM_LINE_NUM = "psalt_line_id";
        static final String PSALM_FAV = "psalt_fav";
        static final String PSALM_HEAD_RU = "_psalt_ru_head";
        static final String PSALM_HEAD_CSL = "_psalt_csl_head";
        static final String PSALM_HEAD_ID = "_id_psalt_head";
        static final String PSALM_CAT_HEAD_ID = "_id_psalt_cat_and_head";
    }
}




















