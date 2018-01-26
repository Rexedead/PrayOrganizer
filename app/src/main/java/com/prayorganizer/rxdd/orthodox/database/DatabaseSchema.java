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

        static final String ICONS_WEB = "icons_web";

        static final String FAV_PRAYS = "prays_fav";
        static final String FAV_PSALMS = "psalt_fav";
        static final String FAV_ICONS = "icons_fav";



    }
    static final class Columns {
        static final String PRAY_ID = "_pray_id";
        static final String PRAY_TEXT = "pray_text";
        static final String PRAY_TITLE = "pray_title";
        static final String PRAY_TAG = "pray_tags";
        static final String CAT_ID = "_cat_id";
        static final String PRAY_MASTER_ID = "_master_id";
        static final String PRAY_MASTER_IMAGE = "img_master";
        static final String PRAY_MASTER_TAG = "tag_master";
        static final String PRAY_MASTER_CATNAME = "cat_master";
        static final String PRAY_SLAVE_ID = "_slave_id";
        static final String PRAY_SLAVE_CATNAME = "cat_slave";
        static final String PRAY_SLAVE_IMAGE = "img_slave";

        static final String PSALM_ID = "_id_psalt";
        static final String PSALM_KAF_ID = "_kaf_id";
        static final String PSALM_CAT_ID = "_id_psalt_cat";
        static final String PSALM_RU = "psalt_ru_full";
        static final String PSALM_CSL = "psalt_csl_full";
        static final String PSALM_TRANSLATE = "psalt_translate";
        static final String PSALM_HEAD_RU = "psalt_name_ru";
        static final String PSALM_HEAD_CSL = "psalt_name_csl";

        static final String ICON_NAME = "icon_name";
        static final String ICON_URL = "icon_url";

        static final String FAV_ID = "_id";



    }
}




















