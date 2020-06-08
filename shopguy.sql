-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 19 Juin 2016 à 17:08
-- Version du serveur :  5.5.36
-- Version de PHP :  5.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `shopguy`
--

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `density` enum('mdpi','hdpi','xhdpi','xxhdpi','xxxhdpi') NOT NULL,
  `icon` blob NOT NULL,
  `filename` varchar(99) NOT NULL,
  PRIMARY KEY (`density`,`filename`),
  UNIQUE KEY `filename` (`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `image`
--

INSERT INTO `image` (`density`, `icon`, `filename`) VALUES
('mdpi', 0x433a5c78616d70705c746d705c706870463830362e746d70, 'beats_solo_2_casque_audio_avec_micro_noir.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463831362e746d70, 'beats_solo_2_casque_audio_avec_micro_rose.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463833382e746d70, 'gucci_kid_shoes.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463834382e746d70, 'gucci_kid_shoes_girl.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463831372e746d70, 'g_pim.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463834392e746d70, 'ic_action_android_exposure_minus_1.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463834412e746d70, 'ic_action_android_plus_one.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463834422e746d70, 'ic_action_back.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463835432e746d70, 'ic_action_black_white_android_t_shirt.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463835442e746d70, 'ic_action_clothing_coat_icon.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463835452e746d70, 'ic_action_clothing_shoe_man_icon.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463835462e746d70, 'ic_action_mobile_headset_icon.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463836302e746d70, 'ic_add_cart_m.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463836312e746d70, 'ic_add_list.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463837312e746d70, 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463837322e746d70, 'ic_adidas_trainingshose_tapered_authentic_trousers_1_0_men_medium_grey_heather_black_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463837332e746d70, 'ic_basket_black_48dp.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463837342e746d70, 'ic_celine_sunglasses_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463837352e746d70, 'ic_chaussures_prada_en_promo_yoox_prada_femme_chaussure_ete_prada_femme_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870463838362e746d70, 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241382e746d70, 'ic_dolce_gabbana_intense_femme1_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241392e746d70, 'ic_dolce_gabbana_shoes_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241412e746d70, 'ic_freud_dream_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241422e746d70, 'ic_freud_ego_and_id_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241442e746d70, 'ic_gucci_eau_de_toilette_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241432e746d70, 'ic_g_pim.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241452e746d70, 'ic_jacket_cuir_dolce_gabani_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364241462e746d70, 'ic_lunette_tods_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364242462e746d70, 'ic_nike_baskets_md_runner_2_chaussures_homme_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243302e746d70, 'ic_nike_women_s_vapor_jacket_sp15_running_windproof_jackets_hot_pink_black_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243312e746d70, 'ic_package_variant_closed_black_48dp.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243322e746d70, 'ic_pants.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243332e746d70, 'ic_settings_black_48dp.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243342e746d70, 'ic_shrimpy_baby_t_shirt_shell_pink_1_packshot_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243352e746d70, 'ic_talon_femme_dolce_gabbana_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243362e746d70, 'ic_zara_homme_collection_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243372e746d70, 'kid_shirt_booby_c.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243382e746d70, 'pantacourt_vs_miss.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243392e746d70, 'rolex_master_watch.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243412e746d70, 'sac_a_dos_souple_pour_enfant_personnalise_hibou.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364243422e746d70, 'sac_gucci_petit_boston_marron_tissu_femme.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364244432e746d70, 'short_adidas_kick_boxing_camo.png'),
('mdpi', 0x433a5c78616d70705c746d705c706870364244442e746d70, 'spinner_triangle.png');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `idprod` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `marque` varchar(30) NOT NULL,
  `annee` year(4) NOT NULL,
  `prix` int(9) NOT NULL,
  `typeclient` enum('Homme','Femme','Enfant') NOT NULL,
  `classproduit` enum('Tricot','Pantalon','Chaussure','Veste','Divers') NOT NULL,
  `description` text NOT NULL,
  `filename` varchar(99) NOT NULL,
  PRIMARY KEY (`idprod`),
  UNIQUE KEY `idprod` (`idprod`),
  KEY `idimg` (`filename`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=275 ;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`idprod`, `nom`, `marque`, `annee`, `prix`, `typeclient`, `classproduit`, `description`, `filename`) VALUES
(183, 'Kid shirt', 'Booby ', 2009, 750, 'Enfant', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'kid_shirt_booby_c.png'),
(184, 'Tricot', 'Shrimpy ', 2009, 750, 'Enfant', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_shrimpy_baby_t_shirt_shell_pink_1_packshot_c.png'),
(185, 'Kid shirt', 'Booby ', 2009, 750, 'Enfant', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'kid_shirt_booby_c.png'),
(186, 'Tricot', 'Shrimpy ', 2009, 750, 'Enfant', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_shrimpy_baby_t_shirt_shell_pink_1_packshot_c.png'),
(187, 'Tricot', 'Adidas ', 2014, 750, 'Homme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
(188, 'Tricot', 'Adidas ', 2014, 750, 'Homme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
(189, 'Tricot', 'Adidas ', 2014, 750, 'Homme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
(190, 'Tricot', 'Adidas ', 2014, 750, 'Homme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
(191, 'Tricot', 'Adidas ', 2014, 750, 'Homme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
(192, 'Tricot', 'Adidas ', 2014, 750, 'Homme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_adidas_city_trefoil_t_shirt_950_c.png'),
(193, 'Tricot', ' Adidas', 2009, 750, 'Femme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
(194, 'Tricot', ' Adidas', 2009, 750, 'Femme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
(195, 'Tricot', ' Adidas', 2009, 750, 'Femme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
(196, 'Tricot', ' Adidas', 2009, 750, 'Femme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
(197, 'Tricot', ' Adidas', 2009, 750, 'Femme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
(198, 'Tricot', ' Adidas', 2009, 750, 'Femme', 'Tricot', 'T-Shirt •  Col Rond •  Manches courtes •  Monnalisa Print •  Mensurations du mannequin : stature 188 cm / poitrine 107 cm / tour de taille 91 cm •  Le mannequin porte une taille M\r\n        MATÉRIAUX\r\n        100% Coton\r\n        COULEUR PRINCIPALE: Noir• Autres couleurs: Olive', 'ic_dolce_et_gabbana_hommeadidas_homme_biz_avisadidas_femme_vetement_neuf_c.png'),
(199, 'Pantsy ', 'Nike ', 2015, 200, 'Femme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'pantacourt_vs_miss.png'),
(200, 'Pantsy ', 'Nike ', 2015, 200, 'Femme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'pantacourt_vs_miss.png'),
(201, 'Pantsy ', 'Nike ', 2015, 200, 'Femme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'pantacourt_vs_miss.png'),
(202, 'Pantsy ', 'Nike ', 2015, 200, 'Femme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'pantacourt_vs_miss.png'),
(203, 'Pantsy ', 'Nike ', 2015, 200, 'Femme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'pantacourt_vs_miss.png'),
(204, 'Kickboxing Short', 'Adidas ', 2009, 750, 'Homme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'short_adidas_kick_boxing_camo.png'),
(205, 'Joking', ' Adidas', 2009, 750, 'Homme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'ic_adidas_trainingshose_tapered_authentic_trousers_1_0_men_medium_grey_heather_black_c.png'),
(206, 'Kickboxing Short', 'Adidas ', 2009, 750, 'Homme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'short_adidas_kick_boxing_camo.png'),
(207, 'Joking', ' Adidas', 2009, 750, 'Homme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'ic_adidas_trainingshose_tapered_authentic_trousers_1_0_men_medium_grey_heather_black_c.png'),
(208, 'Joking', ' Adidas', 2009, 750, 'Homme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'ic_adidas_trainingshose_tapered_authentic_trousers_1_0_men_medium_grey_heather_black_c.png'),
(209, 'Joking', ' Adidas', 2009, 750, 'Homme', 'Pantalon', 'Pantalons •  Ceinture élastique •  Fermeture à lacets •  Poche revolver •  Boutons Tête de Lion •  Mensurations du mannequin : poitrine 102cm / hauteur 188cm / taille 93cm •  Le mannequin porte une taille M •  Collection Versus Versace\r\n        MATÉRIAUX\r\n        66% Polyester, 25% Coton, 9% Elasthanne', 'ic_adidas_trainingshose_tapered_authentic_trousers_1_0_men_medium_grey_heather_black_c.png'),
(210, 'Chaussure', ' Prada', 2009, 750, 'Femme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_chaussures_prada_en_promo_yoox_prada_femme_chaussure_ete_prada_femme_c.png'),
(211, 'Chaussure', 'Nike ', 2009, 750, 'Homme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_nike_baskets_md_runner_2_chaussures_homme_c.png'),
(212, 'Talon', ' Dolce Gabbana', 2009, 750, 'Femme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_talon_femme_dolce_gabbana_c.png'),
(213, 'Shoes', 'Gucci ', 2014, 1000, 'Enfant', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'gucci_kid_shoes.png'),
(214, 'Girl shoes', 'Gucci ', 2015, 900, 'Enfant', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'gucci_kid_shoes_girl.png'),
(215, 'Converse', 'Dolce Gabbana ', 2009, 750, 'Homme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_dolce_gabbana_shoes_c.png'),
(216, 'Chaussure', ' Prada', 2009, 750, 'Femme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_chaussures_prada_en_promo_yoox_prada_femme_chaussure_ete_prada_femme_c.png'),
(217, 'Chaussure', 'Nike ', 2009, 750, 'Homme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_nike_baskets_md_runner_2_chaussures_homme_c.png'),
(218, 'Talon', ' Dolce Gabbana', 2009, 750, 'Femme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_talon_femme_dolce_gabbana_c.png'),
(219, 'Shoes', 'Gucci ', 2014, 1000, 'Enfant', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'gucci_kid_shoes.png'),
(220, 'Girl shoes', 'Gucci ', 2015, 900, 'Enfant', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'gucci_kid_shoes_girl.png'),
(221, 'Converse', 'Dolce Gabbana ', 2009, 750, 'Homme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_dolce_gabbana_shoes_c.png'),
(222, 'Chaussure', ' Prada', 2009, 750, 'Femme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_chaussures_prada_en_promo_yoox_prada_femme_chaussure_ete_prada_femme_c.png'),
(223, 'Chaussure', 'Nike ', 2009, 750, 'Homme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_nike_baskets_md_runner_2_chaussures_homme_c.png'),
(224, 'Talon', ' Dolce Gabbana', 2009, 750, 'Femme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_talon_femme_dolce_gabbana_c.png'),
(225, 'Shoes', 'Gucci ', 2014, 1000, 'Enfant', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'gucci_kid_shoes.png'),
(226, 'Girl shoes', 'Gucci ', 2015, 900, 'Enfant', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'gucci_kid_shoes_girl.png'),
(227, 'Converse', 'Dolce Gabbana ', 2009, 750, 'Homme', 'Chaussure', 'Mocassins •  Pointe ronde •  Birds Print •  Semelle intérieure cuir avec logo •  Détails corde •  Semelle en caoutchouc et en cuir •  Sac à poussière compris  •  • Fabriqué en Italie', 'ic_dolce_gabbana_shoes_c.png'),
(228, 'Jacket', ' Nike', 2009, 750, 'Femme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_nike_women_s_vapor_jacket_sp15_running_windproof_jackets_hot_pink_black_c.png'),
(229, 'Manteau', ' Zara', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_zara_homme_collection_c.png'),
(230, 'Jacket Cuir', 'Dolce ', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_jacket_cuir_dolce_gabani_c.png'),
(231, 'Jacket', ' Nike', 2009, 750, 'Femme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_nike_women_s_vapor_jacket_sp15_running_windproof_jackets_hot_pink_black_c.png'),
(232, 'Manteau', ' Zara', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_zara_homme_collection_c.png'),
(233, 'Jacket Cuir', 'Dolce ', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_jacket_cuir_dolce_gabani_c.png'),
(234, 'Jacket', ' Nike', 2009, 750, 'Femme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_nike_women_s_vapor_jacket_sp15_running_windproof_jackets_hot_pink_black_c.png'),
(235, 'Manteau', ' Zara', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_zara_homme_collection_c.png'),
(236, 'Jacket Cuir', 'Dolce ', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_jacket_cuir_dolce_gabani_c.png'),
(237, 'Jacket', ' Nike', 2009, 750, 'Femme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_nike_women_s_vapor_jacket_sp15_running_windproof_jackets_hot_pink_black_c.png'),
(238, 'Manteau', ' Zara', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_zara_homme_collection_c.png'),
(239, 'Jacket Cuir', 'Dolce ', 2009, 750, 'Homme', 'Veste', 'Vestes •  Manches longues •  Détails en denim •  Deux poches à bouton •  Fermeture éclair •  • Fabriqué en Suisse\r\n        MATÉRIAUX : 90% Coton, 6% Polyamide, 4%', 'ic_jacket_cuir_dolce_gabani_c.png'),
(260, 'Montre', 'Rolex Master', 2010, 2000, 'Homme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'rolex_master_watch.png'),
(261, 'Sac', 'Gucci ', 2011, 500, 'Femme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'sac_gucci_petit_boston_marron_tissu_femme.png'),
(262, 'Casque ', 'Beats Solo 2', 2015, 100, 'Homme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'beats_solo_2_casque_audio_avec_micro_noir.png'),
(263, 'Casque', 'Beats Solo 2', 2015, 100, 'Femme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'beats_solo_2_casque_audio_avec_micro_rose.png'),
(264, 'Lunettes', ' Celine', 2009, 750, 'Femme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'ic_celine_sunglasses_c.png'),
(265, 'Livre', ' Freud Ego end ID', 2009, 750, 'Homme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'ic_freud_ego_and_id_c.png'),
(266, 'Parfum', ' Dolce and Gabbana', 2009, 750, 'Femme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'ic_dolce_gabbana_intense_femme1_c.png'),
(271, 'Livre Freud', 'The dream ', 2009, 750, 'Homme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'ic_freud_dream_c.png'),
(272, 'Lunnete', 'Tod''s ', 2009, 750, 'Homme', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'ic_lunette_tods_c.png'),
(273, 'Sac au dos', 'Nounou', 2010, 2000, 'Enfant', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'g_pim.png'),
(274, 'Sac au dos', 'PouPou ', 2011, 500, 'Enfant', 'Divers', 'Couleur de la Monture : Jaune Transparent •  Couleur du verre: Teinté Azur •  Strass aux tempes •  Logo Versace sur le verre •  • Fabriqué en France', 'sac_a_dos_souple_pour_enfant_personnalise_hibou.png');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_image` FOREIGN KEY (`filename`) REFERENCES `image` (`filename`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
