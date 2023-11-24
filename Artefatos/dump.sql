--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; 
--

CREATE SCHEMA public;



--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; 
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: categoria; Type: TABLE; Schema: public; 
--

CREATE TABLE public.categoria (
    categoria_id integer NOT NULL,
    categoria_nome character varying(255),
    categoria_descricao character varying(255)
);


ALTER TABLE public.categoria OWNER TO ti2cc;

--
-- Name: circuito; Type: TABLE; Schema: public; 
--

CREATE TABLE public.circuito (
    circuito_id integer NOT NULL,
    circuito_nome character varying(255),
    circuito_cidade character varying(255),
    circuito_pais character varying(255),
    circuito_latitude double precision,
    circuito_longitude double precision,
    circuito_altitude integer
);


ALTER TABLE public.circuito OWNER TO ti2cc;

--
-- Name: circuito_circuito_id_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE public.circuito_circuito_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: circuito_circuito_id_seq; Type: SEQUENCE OWNED BY; Schema: public; 
--

ALTER SEQUENCE public.circuito_circuito_id_seq OWNED BY public.circuito.circuito_id;


--
-- Name: equipe; Type: TABLE; Schema: public; 
--

CREATE TABLE public.equipe (
    equipe_id integer NOT NULL,
    equipe_nome character varying(45),
    equipe_nacionalidade character varying(45)
);



--
-- Name: piloto; Type: TABLE; Schema: public; 
--

CREATE TABLE public.piloto (
    piloto_id integer NOT NULL,
    piloto_numero integer,
    piloto_abreviacao text,
    piloto_nome character varying(45),
    piloto_sobrenome text,
    piloto_pais text
);



--
-- Name: post; Type: TABLE; Schema: public; 
--

CREATE TABLE public.post (
    post_id integer NOT NULL,
    post_conteudo text,
    post_data timestamp with time zone,
    post_usuario text NOT NULL,
    post_categoria integer NOT NULL
);



--
-- Name: post_post_id_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE public.post_post_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: post_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; 
--

ALTER SEQUENCE public.post_post_id_seq OWNED BY public.post.post_id;


--
-- Name: resposta_resposta_id; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE public.resposta_resposta_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: resposta; Type: TABLE; Schema: public; 
--

CREATE TABLE public.resposta (
    resposta_id integer DEFAULT nextval('public.resposta_resposta_id'::regclass) NOT NULL,
    resposta_conteudo text,
    resposta_data timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    resposta_post integer NOT NULL,
    resposta_pai_id integer,
    resposta_usuario text,
    resposta_categoria integer
);



--
-- Name: resposta_reposta_pai_id_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE public.resposta_reposta_pai_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: resposta_reposta_pai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; 
--

ALTER SEQUENCE public.resposta_reposta_pai_id_seq OWNED BY public.resposta.resposta_pai_id;


--
-- Name: temporada; Type: TABLE; Schema: public; 
--

CREATE TABLE public.temporada (
    temporada_ano integer NOT NULL
);



--
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE public.user_user_id_seq
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: user; Type: TABLE; Schema: public; 
--

CREATE TABLE public."user" (
    user_id integer DEFAULT nextval('public.user_user_id_seq'::regclass) NOT NULL,
    user_username character varying(16) NOT NULL,
    user_email character varying(255),
    user_senha character varying(32) NOT NULL,
    user_data_criacao timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    user_piloto integer NOT NULL,
    user_equipe integer NOT NULL,
    user_nota integer
);


--
-- Name: circuito circuito_id; Type: DEFAULT; Schema: public; 
--

ALTER TABLE ONLY public.circuito ALTER COLUMN circuito_id SET DEFAULT nextval('public.circuito_circuito_id_seq'::regclass);


--
-- Name: post post_id; Type: DEFAULT; Schema: public; 
--

ALTER TABLE ONLY public.post ALTER COLUMN post_id SET DEFAULT nextval('public.post_post_id_seq'::regclass);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public.categoria VALUES (1, 'jiji', 'hyuhu');
INSERT INTO public.categoria VALUES (2, 'Corridas', 'Tópico para debate sobre as corridas do automobilismo.');
INSERT INTO public.categoria VALUES (3, 'Pilotos', 'Tópico para debater sobre os Pilotos do automobilismo');
INSERT INTO public.categoria VALUES (4, 'Off-Topic', 'Assuntos não relacionados com o automobilismo.');


--
-- Data for Name: circuito; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public.circuito VALUES (1, 'Albert Park Grand Prix Circuit', 'Melbourne', 'Australia', -37.8497, 144968, 10);
INSERT INTO public.circuito VALUES (2, 'Sepang International Circuit', 'Kuala Lumpur', 'Malaysia', 2.76083, 101738, 18);
INSERT INTO public.circuito VALUES (3, 'Bahrain International Circuit', 'Sakhir', 'Bahrain', 26.0325, 50.5106, 7);
INSERT INTO public.circuito VALUES (4, 'Circuit de Barcelona-Catalunya', 'Montmeló', 'Spain', 41.57, 2.26111, 109);
INSERT INTO public.circuito VALUES (5, 'Istanbul Park', 'Istanbul', 'Turkey', 40.9517, 29405, 130);
INSERT INTO public.circuito VALUES (6, 'Circuit de Monaco', 'Monte-Carlo', 'Monaco', 43.7347, 7.42056, 7);
INSERT INTO public.circuito VALUES (7, 'Circuit Gilles Villeneuve', 'Montreal', 'Canada', 45.5, -73.5228, 13);
INSERT INTO public.circuito VALUES (8, 'Circuit de Nevers Magny-Cours', 'Magny Cours', 'France', 46.8642, 3.16361, 228);
INSERT INTO public.circuito VALUES (9, 'Silverstone Circuit', 'Silverstone', 'UK', 52.0786, -1.01694, 153);
INSERT INTO public.circuito VALUES (10, 'Hockenheimring', 'Hockenheim', 'Germany', 49.3278, 8.56583, 103);
INSERT INTO public.circuito VALUES (11, 'Hungaroring', 'Budapest', 'Hungary', 47.5789, 19.2486, 264);
INSERT INTO public.circuito VALUES (12, 'Valencia Street Circuit', 'Valencia', 'Spain', 39.4589, -0.331667, 4);
INSERT INTO public.circuito VALUES (13, 'Circuit de Spa-Francorchamps', 'Spa', 'Belgium', 50.4372, 5.97139, 401);
INSERT INTO public.circuito VALUES (14, 'Autodromo Nazionale di Monza', 'Monza', 'Italy', 45.6156, 9.28111, 162);
INSERT INTO public.circuito VALUES (15, 'Marina Bay Street Circuit', 'Marina Bay', 'Singapore', 1.2914, 103864, 18);
INSERT INTO public.circuito VALUES (16, 'Fuji Speedway', 'Oyama', 'Japan', 35.3717, 138927, 583);
INSERT INTO public.circuito VALUES (17, 'Shanghai International Circuit', 'Shanghai', 'China', 31.3389, 121.22, 5);
INSERT INTO public.circuito VALUES (18, 'Autódromo José Carlos Pace', 'São Paulo', 'Brazil', -23.7036, -46.6997, 785);
INSERT INTO public.circuito VALUES (19, 'Indianapolis Motor Speedway', 'Indianapolis', 'USA', 39795, -86.2347, 223);
INSERT INTO public.circuito VALUES (20, 'Nürburgring', 'Nürburg', 'Germany', 50.3356, 6.9475, 578);
INSERT INTO public.circuito VALUES (21, 'Autodromo Enzo e Dino Ferrari', 'Imola', 'Italy', 44.3439, 11.7167, 37);
INSERT INTO public.circuito VALUES (22, 'Suzuka Circuit', 'Suzuka', 'Japan', 34.8431, 136541, 45);
INSERT INTO public.circuito VALUES (80, 'Las Vegas Strip Street Circuit', 'Las Vegas', 'United States', 36.1147, -115173, NULL);
INSERT INTO public.circuito VALUES (24, 'Yas Marina Circuit', 'Abu Dhabi', 'UAE', 24.4672, 54.6031, 3);
INSERT INTO public.circuito VALUES (25, 'Autódromo Juan y Oscar Gálvez', 'Buenos Aires', 'Argentina', -34.6943, -58.4593, 8);
INSERT INTO public.circuito VALUES (26, 'Circuito de Jerez', 'Jerez de la Frontera', 'Spain', 36.7083, -6.03417, 37);
INSERT INTO public.circuito VALUES (27, 'Autódromo do Estoril', 'Estoril', 'Portugal', 38.7506, -9.39417, 130);
INSERT INTO public.circuito VALUES (28, 'Okayama International Circuit', 'Okayama', 'Japan', 34915, 134221, 266);
INSERT INTO public.circuito VALUES (29, 'Adelaide Street Circuit', 'Adelaide', 'Australia', -34.9272, 138617, 58);
INSERT INTO public.circuito VALUES (30, 'Kyalami', 'Midrand', 'South Africa', -25.9894, 28.0767, 1460);
INSERT INTO public.circuito VALUES (31, 'Donington Park', 'Castle Donington', 'UK', 52.8306, -1.37528, 88);
INSERT INTO public.circuito VALUES (32, 'Autódromo Hermanos Rodríguez', 'Mexico City', 'Mexico', 19.4042, -99.0907, 2227);
INSERT INTO public.circuito VALUES (33, 'Phoenix street circuit', 'Phoenix', 'USA', 33.4479, -112075, 345);
INSERT INTO public.circuito VALUES (34, 'Circuit Paul Ricard', 'Le Castellet', 'France', 43.2506, 5.79167, 432);
INSERT INTO public.circuito VALUES (35, 'Korean International Circuit', 'Yeongam County', 'Korea', 34.7333, 126417, 0);
INSERT INTO public.circuito VALUES (36, 'Autódromo Internacional Nelson Piquet', 'Rio de Janeiro', 'Brazil', -22.9756, -43395, 1126);
INSERT INTO public.circuito VALUES (37, 'Detroit Street Circuit', 'Detroit', 'USA', 42.3298, -83.0401, 177);
INSERT INTO public.circuito VALUES (38, 'Brands Hatch', 'Kent', 'UK', 51.3569, 0.263056, 145);
INSERT INTO public.circuito VALUES (39, 'Circuit Park Zandvoort', 'Zandvoort', 'Netherlands', 52.3888, 4.54092, 6);
INSERT INTO public.circuito VALUES (40, 'Zolder', 'Heusden-Zolder', 'Belgium', 50.9894, 5.25694, 36);
INSERT INTO public.circuito VALUES (41, 'Dijon-Prenois', 'Dijon', 'France', 47.3625, 4.89913, 484);
INSERT INTO public.circuito VALUES (42, 'Fair Park', 'Dallas', 'USA', 32.7774, -96.7587, 139);
INSERT INTO public.circuito VALUES (43, 'Long Beach', 'California', 'USA', 33.7651, -118189, 12);
INSERT INTO public.circuito VALUES (44, 'Las Vegas Street Circuit', 'Nevada', 'USA', 36.1162, -115174, 639);
INSERT INTO public.circuito VALUES (45, 'Jarama', 'Madrid', 'Spain', 40.6171, -3.58558, 609);
INSERT INTO public.circuito VALUES (46, 'Watkins Glen', 'New York State', 'USA', 42.3369, -76.9272, 485);
INSERT INTO public.circuito VALUES (47, 'Scandinavian Raceway', 'Anderstorp', 'Sweden', 57.2653, 13.6042, 153);
INSERT INTO public.circuito VALUES (48, 'Mosport International Raceway', 'Ontario', 'Canada', 44.0481, -78.6756, 332);
INSERT INTO public.circuito VALUES (49, 'Montjuïc', 'Barcelona', 'Spain', 41.3664, 2.15167, 79);
INSERT INTO public.circuito VALUES (50, 'Nivelles-Baulers', 'Brussels', 'Belgium', 50.6211, 4.32694, 139);
INSERT INTO public.circuito VALUES (51, 'Charade Circuit', 'Clermont-Ferrand', 'France', 45.7472, 3.03889, 790);
INSERT INTO public.circuito VALUES (52, 'Circuit Mont-Tremblant', 'Quebec', 'Canada', 46.1877, -74.6099, 214);
INSERT INTO public.circuito VALUES (53, 'Rouen-Les-Essarts', 'Rouen', 'France', 49.3306, 1.00458, 81);
INSERT INTO public.circuito VALUES (54, 'Le Mans', 'Le Mans', 'France', 47.95, 0.224231, 67);
INSERT INTO public.circuito VALUES (55, 'Reims-Gueux', 'Reims', 'France', 49.2542, 3.93083, 88);
INSERT INTO public.circuito VALUES (56, 'Prince George Circuit', 'Eastern Cape Province', 'South Africa', -33.0486, 27.8736, 15);
INSERT INTO public.circuito VALUES (57, 'Zeltweg', 'Styria', 'Austria', 47.2039, 14.7478, 676);
INSERT INTO public.circuito VALUES (58, 'Aintree', 'Liverpool', 'UK', 53.4769, -2.94056, 20);
INSERT INTO public.circuito VALUES (59, 'Circuito da Boavista', 'Oporto', 'Portugal', 41.1705, -8.67325, 28);
INSERT INTO public.circuito VALUES (60, 'Riverside International Raceway', 'California', 'USA', 33937, -117273, 470);
INSERT INTO public.circuito VALUES (61, 'AVUS', 'Berlin', 'Germany', 52.4806, 13.2514, 53);
INSERT INTO public.circuito VALUES (62, 'Monsanto Park Circuit', 'Lisbon', 'Portugal', 38.7197, -9.20306, 158);
INSERT INTO public.circuito VALUES (63, 'Sebring International Raceway', 'Florida', 'USA', 27.4547, -81.3483, 18);
INSERT INTO public.circuito VALUES (64, 'Ain Diab', 'Casablanca', 'Morocco', 33.5786, -7.6875, 19);
INSERT INTO public.circuito VALUES (65, 'Pescara Circuit', 'Pescara', 'Italy', 42475, 14.1508, 129);
INSERT INTO public.circuito VALUES (66, 'Circuit Bremgarten', 'Bern', 'Switzerland', 46.9589, 7.40194, 551);
INSERT INTO public.circuito VALUES (67, 'Circuit de Pedralbes', 'Barcelona', 'Spain', 41.3903, 2.11667, 85);
INSERT INTO public.circuito VALUES (68, 'Buddh International Circuit', 'Uttar Pradesh', 'India', 28.3487, 77.5331, 194);
INSERT INTO public.circuito VALUES (69, 'Circuit of the Americas', 'Austin', 'USA', 30.1328, -97.6411, 161);
INSERT INTO public.circuito VALUES (70, 'Red Bull Ring', 'Spielberg', 'Austria', 47.2197, 14.7647, 678);
INSERT INTO public.circuito VALUES (71, 'Sochi Autodrom', 'Sochi', 'Russia', 43.4057, 39.9578, 2);
INSERT INTO public.circuito VALUES (73, 'Baku City Circuit', 'Baku', 'Azerbaijan', 40.3725, 49.8533, -7);
INSERT INTO public.circuito VALUES (75, 'Autódromo Internacional do Algarve', 'Portimão', 'Portugal', 37227, -8.6267, 108);
INSERT INTO public.circuito VALUES (76, 'Autodromo Internazionale del Mugello', 'Mugello', 'Italy', 43.9975, 11.3719, 255);
INSERT INTO public.circuito VALUES (77, 'Jeddah Corniche Circuit', 'Jeddah', 'Saudi Arabia', 21.6319, 39.1044, 15);
INSERT INTO public.circuito VALUES (78, 'Losail International Circuit', 'Al Daayen', 'Qatar', 25.49, 51.4542, NULL);
INSERT INTO public.circuito VALUES (79, 'Miami International Autodrome', 'Miami', 'USA', 25.9581, -80.2389, NULL);


--
-- Data for Name: equipe; Type: TABLE DATA; Schema: public;
--

INSERT INTO public.equipe VALUES (1, 'adsad', 'abc');
INSERT INTO public.equipe VALUES (2, 'BMW Sauber', 'German');
INSERT INTO public.equipe VALUES (3, 'Williams', 'British');
INSERT INTO public.equipe VALUES (4, 'Renault', 'French');
INSERT INTO public.equipe VALUES (5, 'Toro Rosso', 'Italian');
INSERT INTO public.equipe VALUES (6, 'Ferrari', 'Italian');
INSERT INTO public.equipe VALUES (7, 'Toyota', 'Japanese');
INSERT INTO public.equipe VALUES (8, 'Super Aguri', 'Japanese');
INSERT INTO public.equipe VALUES (9, 'Red Bull', 'Austrian');
INSERT INTO public.equipe VALUES (10, 'Force India', 'Indian');
INSERT INTO public.equipe VALUES (11, 'Honda', 'Japanese');
INSERT INTO public.equipe VALUES (12, 'Spyker', 'Dutch');
INSERT INTO public.equipe VALUES (13, 'MF1', 'Russian');
INSERT INTO public.equipe VALUES (14, 'Spyker MF1', 'Dutch');
INSERT INTO public.equipe VALUES (15, 'Sauber', 'Swiss');
INSERT INTO public.equipe VALUES (16, 'BAR', 'British');
INSERT INTO public.equipe VALUES (17, 'Jordan', 'Irish');
INSERT INTO public.equipe VALUES (18, 'Minardi', 'Italian');
INSERT INTO public.equipe VALUES (19, 'Jaguar', 'British');
INSERT INTO public.equipe VALUES (20, 'Prost', 'French');
INSERT INTO public.equipe VALUES (21, 'Arrows', 'British');
INSERT INTO public.equipe VALUES (22, 'Benetton', 'Italian');
INSERT INTO public.equipe VALUES (23, 'Brawn', 'British');
INSERT INTO public.equipe VALUES (24, 'Stewart', 'British');
INSERT INTO public.equipe VALUES (25, 'Tyrrell', 'British');
INSERT INTO public.equipe VALUES (26, 'Lola', 'British');
INSERT INTO public.equipe VALUES (27, 'Ligier', 'French');
INSERT INTO public.equipe VALUES (28, 'Forti', 'Italian');
INSERT INTO public.equipe VALUES (29, 'Footwork', 'British');
INSERT INTO public.equipe VALUES (30, 'Pacific', 'British');
INSERT INTO public.equipe VALUES (31, 'Simtek', 'British');
INSERT INTO public.equipe VALUES (32, 'Team Lotus', 'British');
INSERT INTO public.equipe VALUES (33, 'Larrousse', 'French');
INSERT INTO public.equipe VALUES (34, 'Brabham', 'British');
INSERT INTO public.equipe VALUES (35, 'Dallara', 'Italian');
INSERT INTO public.equipe VALUES (36, 'Fondmetal', 'Italian');
INSERT INTO public.equipe VALUES (37, 'March', 'British');
INSERT INTO public.equipe VALUES (38, 'Andrea Moda', 'Italian');
INSERT INTO public.equipe VALUES (39, 'AGS', 'French');
INSERT INTO public.equipe VALUES (40, 'Lambo', 'Italian');
INSERT INTO public.equipe VALUES (41, 'Leyton House', 'British');
INSERT INTO public.equipe VALUES (42, 'Coloni', 'Italian');
INSERT INTO public.equipe VALUES (44, 'Euro Brun', 'Italian');
INSERT INTO public.equipe VALUES (45, 'Osella', 'Italian');
INSERT INTO public.equipe VALUES (46, 'Onyx', 'British');
INSERT INTO public.equipe VALUES (47, 'Life', 'Italian');
INSERT INTO public.equipe VALUES (48, 'Rial', 'German');
INSERT INTO public.equipe VALUES (49, 'Zakspeed', 'German');
INSERT INTO public.equipe VALUES (50, 'RAM', 'British');
INSERT INTO public.equipe VALUES (51, 'Alfa Romeo', 'Swiss');
INSERT INTO public.equipe VALUES (52, 'Spirit', 'British');
INSERT INTO public.equipe VALUES (53, 'Toleman', 'British');
INSERT INTO public.equipe VALUES (54, 'ATS', 'Italian');
INSERT INTO public.equipe VALUES (55, 'Theodore', 'Hong Kong');
INSERT INTO public.equipe VALUES (56, 'Fittipaldi', 'Brazilian');
INSERT INTO public.equipe VALUES (57, 'Ensign', 'British');
INSERT INTO public.equipe VALUES (58, 'Shadow', 'British');
INSERT INTO public.equipe VALUES (59, 'Wolf', 'Canadian');
INSERT INTO public.equipe VALUES (60, 'Merzario', 'Italian');
INSERT INTO public.equipe VALUES (61, 'Kauhsen', 'German');
INSERT INTO public.equipe VALUES (62, 'Rebaque', 'Mexican');
INSERT INTO public.equipe VALUES (63, 'Surtees', 'British');
INSERT INTO public.equipe VALUES (64, 'Hesketh', 'British');
INSERT INTO public.equipe VALUES (65, 'Martini', 'French');
INSERT INTO public.equipe VALUES (66, 'BRM', 'British');
INSERT INTO public.equipe VALUES (67, 'Penske', 'American');
INSERT INTO public.equipe VALUES (68, 'LEC', 'British');
INSERT INTO public.equipe VALUES (69, 'McGuire', 'Australian');
INSERT INTO public.equipe VALUES (70, 'Boro', 'Dutch');
INSERT INTO public.equipe VALUES (71, 'Apollon', 'Swiss');
INSERT INTO public.equipe VALUES (72, 'Kojima', 'Japanese');
INSERT INTO public.equipe VALUES (73, 'Parnelli', 'American');
INSERT INTO public.equipe VALUES (74, 'Maki', 'Japanese');
INSERT INTO public.equipe VALUES (75, 'Embassy Hill', 'British');
INSERT INTO public.equipe VALUES (76, 'Lyncar', 'British');
INSERT INTO public.equipe VALUES (77, 'Trojan', 'British');
INSERT INTO public.equipe VALUES (78, 'Amon', 'New Zealander');
INSERT INTO public.equipe VALUES (79, 'Token', 'British');
INSERT INTO public.equipe VALUES (80, 'Iso Marlboro', 'British');
INSERT INTO public.equipe VALUES (81, 'Tecno', 'Italian');
INSERT INTO public.equipe VALUES (82, 'Matra', 'French');
INSERT INTO public.equipe VALUES (83, 'Politoys', 'British');
INSERT INTO public.equipe VALUES (84, 'Connew', 'British');
INSERT INTO public.equipe VALUES (85, 'Bellasi', 'Swiss');
INSERT INTO public.equipe VALUES (86, 'De Tomaso', 'Italian');
INSERT INTO public.equipe VALUES (87, 'Cooper', 'British');
INSERT INTO public.equipe VALUES (88, 'Eagle', 'American');
INSERT INTO public.equipe VALUES (89, 'LDS', 'South African');
INSERT INTO public.equipe VALUES (90, 'Protos', 'British');
INSERT INTO public.equipe VALUES (91, 'Shannon', 'British');
INSERT INTO public.equipe VALUES (92, 'Scirocco', 'British');
INSERT INTO public.equipe VALUES (93, 'RE', 'Rhodesian');
INSERT INTO public.equipe VALUES (94, 'BRP', 'British');
INSERT INTO public.equipe VALUES (95, 'Porsche', 'German');
INSERT INTO public.equipe VALUES (96, 'Derrington', 'British');
INSERT INTO public.equipe VALUES (97, 'Gilby', 'British');
INSERT INTO public.equipe VALUES (98, 'Stebro', 'Canadian');
INSERT INTO public.equipe VALUES (99, 'Emeryson', 'British');
INSERT INTO public.equipe VALUES (100, 'ENB', 'Belgian');
INSERT INTO public.equipe VALUES (101, 'JBW', 'British');
INSERT INTO public.equipe VALUES (102, 'Ferguson', 'British');
INSERT INTO public.equipe VALUES (103, 'MBM', 'Swiss');
INSERT INTO public.equipe VALUES (104, 'Behra-Porsche', 'Italian');
INSERT INTO public.equipe VALUES (105, 'Maserati', 'Italian');
INSERT INTO public.equipe VALUES (106, 'Scarab', 'American');
INSERT INTO public.equipe VALUES (107, 'Watson', 'American');
INSERT INTO public.equipe VALUES (108, 'Epperly', 'American');
INSERT INTO public.equipe VALUES (109, 'Phillips', 'American');
INSERT INTO public.equipe VALUES (110, 'Lesovsky', 'American');
INSERT INTO public.equipe VALUES (111, 'Trevis', 'American');
INSERT INTO public.equipe VALUES (112, 'Meskowski', 'American');
INSERT INTO public.equipe VALUES (113, 'Kurtis Kraft', 'American');
INSERT INTO public.equipe VALUES (114, 'Kuzma', 'American');
INSERT INTO public.equipe VALUES (115, 'Christensen', 'American');
INSERT INTO public.equipe VALUES (116, 'Ewing', 'American');
INSERT INTO public.equipe VALUES (117, 'Aston Martin', 'British');
INSERT INTO public.equipe VALUES (118, 'Vanwall', 'British');
INSERT INTO public.equipe VALUES (119, 'Moore', 'American');
INSERT INTO public.equipe VALUES (120, 'Dunn', 'American');
INSERT INTO public.equipe VALUES (121, 'Elder', 'American');
INSERT INTO public.equipe VALUES (122, 'Sutton', 'American');
INSERT INTO public.equipe VALUES (123, 'Fry', 'British');
INSERT INTO public.equipe VALUES (124, 'Tec-Mec', 'Italian');
INSERT INTO public.equipe VALUES (125, 'Connaught', 'British');
INSERT INTO public.equipe VALUES (126, 'Alta', 'British');
INSERT INTO public.equipe VALUES (127, 'OSCA', 'Italian');
INSERT INTO public.equipe VALUES (128, 'Gordini', 'French');
INSERT INTO public.equipe VALUES (129, 'Stevens', 'American');
INSERT INTO public.equipe VALUES (130, 'Bugatti', 'French');
INSERT INTO public.equipe VALUES (131, 'Mercedes', 'German');
INSERT INTO public.equipe VALUES (132, 'Lancia', 'Italian');
INSERT INTO public.equipe VALUES (133, 'HWM', 'British');
INSERT INTO public.equipe VALUES (134, 'Schroeder', 'American');
INSERT INTO public.equipe VALUES (135, 'Pawl', 'American');
INSERT INTO public.equipe VALUES (136, 'Pankratz', 'American');
INSERT INTO public.equipe VALUES (137, 'Arzani-Volpini', 'Italian');
INSERT INTO public.equipe VALUES (138, 'Nichels', 'American');
INSERT INTO public.equipe VALUES (139, 'Bromme', 'American');
INSERT INTO public.equipe VALUES (140, 'Klenk', 'German');
INSERT INTO public.equipe VALUES (141, 'Simca', 'French');
INSERT INTO public.equipe VALUES (142, 'Turner', 'American');
INSERT INTO public.equipe VALUES (143, 'Del Roy', 'American');
INSERT INTO public.equipe VALUES (144, 'Veritas', 'German');
INSERT INTO public.equipe VALUES (145, 'BMW', 'German');
INSERT INTO public.equipe VALUES (146, 'EMW', 'East German');
INSERT INTO public.equipe VALUES (147, 'AFM', 'German');
INSERT INTO public.equipe VALUES (148, 'Frazer Nash', 'British');
INSERT INTO public.equipe VALUES (149, 'Sherman', 'American');
INSERT INTO public.equipe VALUES (150, 'Deidt', 'American');
INSERT INTO public.equipe VALUES (151, 'ERA', 'British');
INSERT INTO public.equipe VALUES (152, 'Aston Butterworth', 'British');
INSERT INTO public.equipe VALUES (153, 'Cisitalia', 'Italian');
INSERT INTO public.equipe VALUES (154, 'Talbot-Lago', 'French');
INSERT INTO public.equipe VALUES (155, 'Hall', 'American');
INSERT INTO public.equipe VALUES (156, 'Marchese', 'American');
INSERT INTO public.equipe VALUES (157, 'Langley', 'American');
INSERT INTO public.equipe VALUES (158, 'Rae', 'American');
INSERT INTO public.equipe VALUES (159, 'Olson', 'American');
INSERT INTO public.equipe VALUES (160, 'Wetteroth', 'American');
INSERT INTO public.equipe VALUES (161, 'Adams', 'American');
INSERT INTO public.equipe VALUES (162, 'Snowberger', 'American');
INSERT INTO public.equipe VALUES (163, 'Milano', 'Italian');
INSERT INTO public.equipe VALUES (164, 'HRT', 'Spanish');
INSERT INTO public.equipe VALUES (167, 'Cooper-Maserati', 'British');
INSERT INTO public.equipe VALUES (166, 'Virgin', 'British');
INSERT INTO public.equipe VALUES (168, 'Cooper-OSCA', 'British');
INSERT INTO public.equipe VALUES (169, 'Cooper-Borgward', 'British');
INSERT INTO public.equipe VALUES (170, 'Cooper-Climax', 'British');
INSERT INTO public.equipe VALUES (171, 'Cooper-Castellotti', 'British');
INSERT INTO public.equipe VALUES (172, 'Lotus-Climax', 'British');
INSERT INTO public.equipe VALUES (173, 'Lotus-Maserati', 'British');
INSERT INTO public.equipe VALUES (174, 'De Tomaso-Osca', 'Italian');
INSERT INTO public.equipe VALUES (175, 'De Tomaso-Alfa Romeo', 'Italian');
INSERT INTO public.equipe VALUES (176, 'Lotus-BRM', 'British');
INSERT INTO public.equipe VALUES (177, 'Lotus-Borgward', 'British');
INSERT INTO public.equipe VALUES (178, 'Cooper-Alfa Romeo', 'British');
INSERT INTO public.equipe VALUES (179, 'De Tomaso-Ferrari', 'Italian');
INSERT INTO public.equipe VALUES (180, 'Lotus-Ford', 'British');
INSERT INTO public.equipe VALUES (181, 'Brabham-BRM', 'British');
INSERT INTO public.equipe VALUES (182, 'Brabham-Ford', 'British');
INSERT INTO public.equipe VALUES (183, 'Brabham-Climax', 'British');
INSERT INTO public.equipe VALUES (184, 'LDS-Climax', 'South African');
INSERT INTO public.equipe VALUES (185, 'LDS-Alfa Romeo', 'South African');
INSERT INTO public.equipe VALUES (186, 'Cooper-Ford', 'British');
INSERT INTO public.equipe VALUES (187, 'McLaren-Ford', 'British');
INSERT INTO public.equipe VALUES (188, 'McLaren-Serenissima', 'British');
INSERT INTO public.equipe VALUES (189, 'Eagle-Climax', 'American');
INSERT INTO public.equipe VALUES (190, 'Eagle-Weslake', 'American');
INSERT INTO public.equipe VALUES (191, 'Brabham-Repco', 'British');
INSERT INTO public.equipe VALUES (192, 'Cooper-Ferrari', 'British');
INSERT INTO public.equipe VALUES (193, 'Cooper-ATS', 'British');
INSERT INTO public.equipe VALUES (194, 'McLaren-BRM', 'British');
INSERT INTO public.equipe VALUES (195, 'Cooper-BRM', 'British');
INSERT INTO public.equipe VALUES (196, 'Matra-Ford', 'French');
INSERT INTO public.equipe VALUES (197, 'BRM-Ford', 'British');
INSERT INTO public.equipe VALUES (198, 'McLaren-Alfa Romeo', 'British');
INSERT INTO public.equipe VALUES (199, 'March-Alfa Romeo', 'British');
INSERT INTO public.equipe VALUES (200, 'March-Ford', 'British');
INSERT INTO public.equipe VALUES (201, 'Lotus-Pratt &amp; Whitney', 'British');
INSERT INTO public.equipe VALUES (202, 'Shadow-Ford', 'British');
INSERT INTO public.equipe VALUES (203, 'Shadow-Matra', 'British');
INSERT INTO public.equipe VALUES (204, 'Brabham-Alfa Romeo', 'British');
INSERT INTO public.equipe VALUES (205, 'Lotus', 'Malaysian');
INSERT INTO public.equipe VALUES (206, 'Marussia', 'Russian');
INSERT INTO public.equipe VALUES (207, 'Caterham', 'Malaysian');
INSERT INTO public.equipe VALUES (208, 'Lotus F1', 'British');
INSERT INTO public.equipe VALUES (209, 'Manor Marussia', 'British');
INSERT INTO public.equipe VALUES (210, 'Haas F1 Team', 'American');
INSERT INTO public.equipe VALUES (211, 'Racing Point', 'British');
INSERT INTO public.equipe VALUES (213, 'AlphaTauri', 'Italian');
INSERT INTO public.equipe VALUES (214, 'Alpine F1 Team', 'French');


--
-- Data for Name: piloto; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public.piloto VALUES (1, 10, 'abc', 'asas', NULL, NULL);
INSERT INTO public.piloto VALUES (2, NULL, 'HEI', 'Nick', 'Heidfeld', 'German');
INSERT INTO public.piloto VALUES (3, 6, 'ROS', 'Nico', 'Rosberg', 'German');
INSERT INTO public.piloto VALUES (4, 14, 'ALO', 'Fernando', 'Alonso', 'Spanish');
INSERT INTO public.piloto VALUES (5, NULL, 'KOV', 'Heikki', 'Kovalainen', 'Finnish');
INSERT INTO public.piloto VALUES (6, NULL, 'NAK', 'Kazuki', 'Nakajima', 'Japanese');
INSERT INTO public.piloto VALUES (7, NULL, 'BOU', 'Sébastien', 'Bourdais', 'French');
INSERT INTO public.piloto VALUES (8, 7, 'RAI', 'Kimi', 'Räikkönen', 'Finnish');
INSERT INTO public.piloto VALUES (9, 88, 'KUB', 'Robert', 'Kubica', 'Polish');
INSERT INTO public.piloto VALUES (10, NULL, 'GLO', 'Timo', 'Glock', 'German');
INSERT INTO public.piloto VALUES (11, NULL, 'SAT', 'Takuma', 'Sato', 'Japanese');
INSERT INTO public.piloto VALUES (12, NULL, 'PIQ', 'Nelson', 'Piquet Jr.', 'Brazilian');
INSERT INTO public.piloto VALUES (13, 19, 'MAS', 'Felipe', 'Massa', 'Brazilian');
INSERT INTO public.piloto VALUES (14, NULL, 'COU', 'David', 'Coulthard', 'British');
INSERT INTO public.piloto VALUES (15, NULL, 'TRU', 'Jarno', 'Trulli', 'Italian');
INSERT INTO public.piloto VALUES (16, 99, 'SUT', 'Adrian', 'Sutil', 'German');
INSERT INTO public.piloto VALUES (17, NULL, 'WEB', 'Mark', 'Webber', 'Australian');
INSERT INTO public.piloto VALUES (18, 22, 'BUT', 'Jenson', 'Button', 'British');
INSERT INTO public.piloto VALUES (19, NULL, 'DAV', 'Anthony', 'Davidson', 'British');
INSERT INTO public.piloto VALUES (20, 5, 'VET', 'Sebastian', 'Vettel', 'German');
INSERT INTO public.piloto VALUES (21, NULL, 'FIS', 'Giancarlo', 'Fisichella', 'Italian');
INSERT INTO public.piloto VALUES (22, NULL, 'BAR', 'Rubens', 'Barrichello', 'Brazilian');
INSERT INTO public.piloto VALUES (23, NULL, 'SCH', 'Ralf', 'Schumacher', 'German');
INSERT INTO public.piloto VALUES (24, NULL, 'LIU', 'Vitantonio', 'Liuzzi', 'Italian');
INSERT INTO public.piloto VALUES (25, NULL, 'WUR', 'Alexander', 'Wurz', 'Austrian');
INSERT INTO public.piloto VALUES (26, NULL, 'SPE', 'Scott', 'Speed', 'American');
INSERT INTO public.piloto VALUES (27, NULL, 'ALB', 'Christijan', 'Albers', 'Dutch');
INSERT INTO public.piloto VALUES (28, NULL, 'WIN', 'Markus', 'Winkelhock', 'German');
INSERT INTO public.piloto VALUES (29, NULL, 'YAM', 'Sakon', 'Yamamoto', 'Japanese');
INSERT INTO public.piloto VALUES (30, NULL, 'MSC', 'Michael', 'Schumacher', 'German');
INSERT INTO public.piloto VALUES (31, NULL, 'MON', 'Juan', 'Pablo Montoya', 'Colombian');
INSERT INTO public.piloto VALUES (32, NULL, 'KLI', 'Christian', 'Klien', 'Austrian');
INSERT INTO public.piloto VALUES (33, NULL, 'TMO', 'Tiago', 'Monteiro', 'Portuguese');
INSERT INTO public.piloto VALUES (34, NULL, 'IDE', 'Yuji', 'Ide', 'Japanese');
INSERT INTO public.piloto VALUES (35, NULL, 'VIL', 'Jacques', 'Villeneuve', 'Canadian');
INSERT INTO public.piloto VALUES (36, NULL, 'FMO', 'Franck', 'Montagny', 'French');
INSERT INTO public.piloto VALUES (37, NULL, 'DLR', 'Pedro', 'de la Rosa', 'Spanish');
INSERT INTO public.piloto VALUES (38, NULL, 'DOO', 'Robert', 'Doornbos', 'Dutch');
INSERT INTO public.piloto VALUES (39, NULL, 'KAR', 'Narain', 'Karthikeyan', 'Indian');
INSERT INTO public.piloto VALUES (40, NULL, 'FRI', 'Patrick', 'Friesacher', 'Austrian');
INSERT INTO public.piloto VALUES (41, NULL, 'ZON', 'Ricardo', 'Zonta', 'Brazilian');
INSERT INTO public.piloto VALUES (42, NULL, 'PIZ', 'Antônio', 'Pizzonia', 'Brazilian');
INSERT INTO public.piloto VALUES (43, NULL, NULL, 'Cristiano', 'da Matta', 'Brazilian');
INSERT INTO public.piloto VALUES (44, NULL, NULL, 'Olivier', 'Panis', 'French');
INSERT INTO public.piloto VALUES (45, NULL, NULL, 'Giorgio', 'Pantano', 'Italian');
INSERT INTO public.piloto VALUES (46, NULL, NULL, 'Gianmaria', 'Bruni', 'Italian');
INSERT INTO public.piloto VALUES (47, NULL, NULL, 'Zsolt', 'Baumgartner', 'Hungarian');
INSERT INTO public.piloto VALUES (48, NULL, NULL, 'Marc', 'Gené', 'Spanish');
INSERT INTO public.piloto VALUES (49, NULL, NULL, 'Heinz-Harald', 'Frentzen', 'German');
INSERT INTO public.piloto VALUES (50, NULL, NULL, 'Jos', 'Verstappen', 'Dutch');
INSERT INTO public.piloto VALUES (51, NULL, NULL, 'Justin', 'Wilson', 'British');
INSERT INTO public.piloto VALUES (52, NULL, NULL, 'Ralph', 'Firman', 'Irish');
INSERT INTO public.piloto VALUES (53, NULL, NULL, 'Nicolas', 'Kiesa', 'Danish');
INSERT INTO public.piloto VALUES (54, NULL, NULL, 'Luciano', 'Burti', 'Brazilian');
INSERT INTO public.piloto VALUES (55, NULL, NULL, 'Jean', 'Alesi', 'French');
INSERT INTO public.piloto VALUES (56, NULL, NULL, 'Eddie', 'Irvine', 'British');
INSERT INTO public.piloto VALUES (57, NULL, NULL, 'Mika', 'Häkkinen', 'Finnish');
INSERT INTO public.piloto VALUES (58, NULL, NULL, 'Tarso', 'Marques', 'Brazilian');
INSERT INTO public.piloto VALUES (59, NULL, NULL, 'Enrique', 'Bernoldi', 'Brazilian');
INSERT INTO public.piloto VALUES (60, NULL, NULL, 'Gastón', 'Mazzacane', 'Argentine');
INSERT INTO public.piloto VALUES (61, NULL, NULL, 'Tomáš', 'Enge', 'Czech');
INSERT INTO public.piloto VALUES (62, NULL, NULL, 'Alex', 'Yoong', 'Malaysian');
INSERT INTO public.piloto VALUES (63, NULL, NULL, 'Mika', 'Salo', 'Finnish');
INSERT INTO public.piloto VALUES (64, NULL, NULL, 'Pedro', 'Diniz', 'Brazilian');
INSERT INTO public.piloto VALUES (65, NULL, NULL, 'Johnny', 'Herbert', 'British');
INSERT INTO public.piloto VALUES (66, NULL, NULL, 'Allan', 'McNish', 'British');
INSERT INTO public.piloto VALUES (67, NULL, 'BUE', 'Sébastien', 'Buemi', 'Swiss');
INSERT INTO public.piloto VALUES (68, NULL, NULL, 'Toranosuke', 'Takagi', 'Japanese');
INSERT INTO public.piloto VALUES (69, NULL, 'BAD', 'Luca', 'Badoer', 'Italian');
INSERT INTO public.piloto VALUES (70, NULL, NULL, 'Alessandro', 'Zanardi', 'Italian');
INSERT INTO public.piloto VALUES (71, NULL, NULL, 'Damon', 'Hill', 'British');
INSERT INTO public.piloto VALUES (72, NULL, NULL, 'Stéphane', 'Sarrazin', 'French');
INSERT INTO public.piloto VALUES (73, NULL, NULL, 'Ricardo', 'Rosset', 'Brazilian');
INSERT INTO public.piloto VALUES (74, NULL, NULL, 'Esteban', 'Tuero', 'Argentine');
INSERT INTO public.piloto VALUES (75, NULL, NULL, 'Shinji', 'Nakano', 'Japanese');
INSERT INTO public.piloto VALUES (76, NULL, 'MAG', 'Jan', 'Magnussen', 'Danish');
INSERT INTO public.piloto VALUES (77, NULL, NULL, 'Gerhard', 'Berger', 'Austrian');
INSERT INTO public.piloto VALUES (78, NULL, NULL, 'Nicola', 'Larini', 'Italian');
INSERT INTO public.piloto VALUES (79, NULL, NULL, 'Ukyo', 'Katayama', 'Japanese');
INSERT INTO public.piloto VALUES (80, NULL, NULL, 'Vincenzo', 'Sospiri', 'Italian');
INSERT INTO public.piloto VALUES (81, NULL, NULL, 'Gianni', 'Morbidelli', 'Italian');
INSERT INTO public.piloto VALUES (82, NULL, NULL, 'Norberto', 'Fontana', 'Argentine');
INSERT INTO public.piloto VALUES (83, NULL, NULL, 'Pedro', 'Lamy', 'Portuguese');
INSERT INTO public.piloto VALUES (84, NULL, NULL, 'Martin', 'Brundle', 'British');
INSERT INTO public.piloto VALUES (85, NULL, NULL, 'Andrea', 'Montermini', 'Italian');
INSERT INTO public.piloto VALUES (86, NULL, NULL, 'Giovanni', 'Lavaggi', 'Italian');
INSERT INTO public.piloto VALUES (87, NULL, NULL, 'Mark', 'Blundell', 'British');
INSERT INTO public.piloto VALUES (88, NULL, NULL, 'Aguri', 'Suzuki', 'Japanese');
INSERT INTO public.piloto VALUES (89, NULL, NULL, 'Taki', 'Inoue', 'Japanese');
INSERT INTO public.piloto VALUES (90, NULL, NULL, 'Roberto', 'Moreno', 'Brazilian');
INSERT INTO public.piloto VALUES (91, NULL, NULL, 'Karl', 'Wendlinger', 'Austrian');
INSERT INTO public.piloto VALUES (92, NULL, NULL, 'Bertrand', 'Gachot', 'Belgian');
INSERT INTO public.piloto VALUES (93, NULL, NULL, 'Domenico', 'Schiattarella', 'Italian');
INSERT INTO public.piloto VALUES (94, NULL, NULL, 'Pierluigi', 'Martini', 'Italian');
INSERT INTO public.piloto VALUES (95, NULL, NULL, 'Nigel', 'Mansell', 'British');
INSERT INTO public.piloto VALUES (96, NULL, NULL, 'Jean-Christophe', 'Boullion', 'French');
INSERT INTO public.piloto VALUES (97, NULL, NULL, 'Massimiliano', 'Papis', 'Italian');
INSERT INTO public.piloto VALUES (98, NULL, NULL, 'Jean-Denis', 'Délétraz', 'Swiss');
INSERT INTO public.piloto VALUES (99, NULL, NULL, 'Gabriele', 'Tarquini', 'Italian');
INSERT INTO public.piloto VALUES (100, NULL, NULL, 'Érik', 'Comas', 'French');
INSERT INTO public.piloto VALUES (101, NULL, NULL, 'David', 'Brabham', 'Australian');
INSERT INTO public.piloto VALUES (102, NULL, NULL, 'Ayrton', 'Senna', 'Brazilian');
INSERT INTO public.piloto VALUES (103, NULL, NULL, 'Éric', 'Bernard', 'French');
INSERT INTO public.piloto VALUES (104, NULL, NULL, 'Christian', 'Fittipaldi', 'Brazilian');
INSERT INTO public.piloto VALUES (105, NULL, NULL, 'Michele', 'Alboreto', 'Italian');
INSERT INTO public.piloto VALUES (106, NULL, NULL, 'Olivier', 'Beretta', 'Monegasque');
INSERT INTO public.piloto VALUES (107, NULL, NULL, 'Roland', 'Ratzenberger', 'Austrian');
INSERT INTO public.piloto VALUES (108, NULL, NULL, 'Paul', 'Belmondo', 'French');
INSERT INTO public.piloto VALUES (109, NULL, NULL, 'Jyrki', 'Järvilehto', 'Finnish');
INSERT INTO public.piloto VALUES (110, NULL, NULL, 'Andrea', 'de Cesaris', 'Italian');
INSERT INTO public.piloto VALUES (111, NULL, NULL, 'Jean-Marc', 'Gounon', 'French');
INSERT INTO public.piloto VALUES (112, NULL, NULL, 'Philippe', 'Alliot', 'French');
INSERT INTO public.piloto VALUES (113, NULL, NULL, 'Philippe', 'Adams', 'Belgian');
INSERT INTO public.piloto VALUES (114, NULL, NULL, 'Yannick', 'Dalmas', 'French');
INSERT INTO public.piloto VALUES (115, NULL, NULL, 'Hideki', 'Noda', 'Japanese');
INSERT INTO public.piloto VALUES (116, NULL, NULL, 'Franck', 'Lagorce', 'French');
INSERT INTO public.piloto VALUES (117, NULL, NULL, 'Alain', 'Prost', 'French');
INSERT INTO public.piloto VALUES (118, NULL, NULL, 'Derek', 'Warwick', 'British');
INSERT INTO public.piloto VALUES (119, NULL, NULL, 'Riccardo', 'Patrese', 'Italian');
INSERT INTO public.piloto VALUES (120, NULL, NULL, 'Fabrizio', 'Barbazza', 'Italian');
INSERT INTO public.piloto VALUES (121, NULL, NULL, 'Michael', 'Andretti', 'American');
INSERT INTO public.piloto VALUES (122, NULL, NULL, 'Ivan', 'Capelli', 'Italian');
INSERT INTO public.piloto VALUES (123, NULL, NULL, 'Thierry', 'Boutsen', 'Belgian');
INSERT INTO public.piloto VALUES (124, NULL, NULL, 'Marco', 'Apicella', 'Italian');
INSERT INTO public.piloto VALUES (125, NULL, NULL, 'Emanuele', 'Naspetti', 'Italian');
INSERT INTO public.piloto VALUES (126, NULL, NULL, 'Toshio', 'Suzuki', 'Japanese');
INSERT INTO public.piloto VALUES (127, NULL, NULL, 'Maurício', 'Gugelmin', 'Brazilian');
INSERT INTO public.piloto VALUES (128, NULL, NULL, 'Eric', 'van de Poele', 'Belgian');
INSERT INTO public.piloto VALUES (129, NULL, NULL, 'Olivier', 'Grouillard', 'French');
INSERT INTO public.piloto VALUES (130, NULL, NULL, 'Andrea', 'Chiesa', 'Swiss');
INSERT INTO public.piloto VALUES (131, NULL, NULL, 'Stefano', 'Modena', 'Italian');
INSERT INTO public.piloto VALUES (132, NULL, NULL, 'Giovanna', 'Amati', 'Italian');
INSERT INTO public.piloto VALUES (133, NULL, NULL, 'Alex', 'Caffi', 'Italian');
INSERT INTO public.piloto VALUES (134, NULL, NULL, 'Enrico', 'Bertaggia', 'Italian');
INSERT INTO public.piloto VALUES (135, NULL, NULL, 'Perry', 'McCarthy', 'British');
INSERT INTO public.piloto VALUES (136, NULL, NULL, 'Jan', 'Lammers', 'Dutch');
INSERT INTO public.piloto VALUES (137, NULL, NULL, 'Nelson', 'Piquet', 'Brazilian');
INSERT INTO public.piloto VALUES (138, NULL, NULL, 'Satoru', 'Nakajima', 'Japanese');
INSERT INTO public.piloto VALUES (139, NULL, NULL, 'Emanuele', 'Pirro', 'Italian');
INSERT INTO public.piloto VALUES (140, NULL, NULL, 'Stefan', 'Johansson', 'Swedish');
INSERT INTO public.piloto VALUES (141, NULL, NULL, 'Julian', 'Bailey', 'British');
INSERT INTO public.piloto VALUES (142, NULL, NULL, 'Pedro', 'Chaves', 'Portuguese');
INSERT INTO public.piloto VALUES (143, NULL, NULL, 'Michael', 'Bartels', 'German');
INSERT INTO public.piloto VALUES (144, NULL, NULL, 'Naoki', 'Hattori', 'Japanese');
INSERT INTO public.piloto VALUES (145, NULL, NULL, 'Alessandro', 'Nannini', 'Italian');
INSERT INTO public.piloto VALUES (146, NULL, NULL, 'Bernd', 'Schneider', 'German');
INSERT INTO public.piloto VALUES (147, NULL, NULL, 'Paolo', 'Barilla', 'Italian');
INSERT INTO public.piloto VALUES (148, NULL, NULL, 'Gregor', 'Foitek', 'Swiss');
INSERT INTO public.piloto VALUES (149, NULL, NULL, 'Claudio', 'Langes', 'Italian');
INSERT INTO public.piloto VALUES (150, NULL, NULL, 'Gary', 'Brabham', 'Australian');
INSERT INTO public.piloto VALUES (151, NULL, NULL, 'Martin', 'Donnelly', 'British');
INSERT INTO public.piloto VALUES (152, NULL, NULL, 'Bruno', 'Giacomelli', 'Italian');
INSERT INTO public.piloto VALUES (153, NULL, 'ALG', 'Jaime', 'Alguersuari', 'Spanish');
INSERT INTO public.piloto VALUES (154, 8, 'GRO', 'Romain', 'Grosjean', 'French');
INSERT INTO public.piloto VALUES (155, 10, 'KOB', 'Kamui', 'Kobayashi', 'Japanese');
INSERT INTO public.piloto VALUES (156, NULL, NULL, 'Jonathan', 'Palmer', 'British');
INSERT INTO public.piloto VALUES (157, NULL, NULL, 'Christian', 'Danner', 'German');
INSERT INTO public.piloto VALUES (158, NULL, NULL, 'Eddie', 'Cheever', 'American');
INSERT INTO public.piloto VALUES (159, NULL, NULL, 'Luis', 'Pérez-Sala', 'Spanish');
INSERT INTO public.piloto VALUES (160, NULL, NULL, 'Piercarlo', 'Ghinzani', 'Italian');
INSERT INTO public.piloto VALUES (161, NULL, NULL, 'Volker', 'Weidler', 'German');
INSERT INTO public.piloto VALUES (162, NULL, NULL, 'Pierre-Henri', 'Raphanel', 'French');
INSERT INTO public.piloto VALUES (163, NULL, NULL, 'René', 'Arnoux', 'French');
INSERT INTO public.piloto VALUES (164, NULL, NULL, 'Joachim', 'Winkelhock', 'German');
INSERT INTO public.piloto VALUES (165, NULL, NULL, 'Oscar', 'Larrauri', 'Argentine');
INSERT INTO public.piloto VALUES (166, NULL, NULL, 'Philippe', 'Streiff', 'French');
INSERT INTO public.piloto VALUES (167, NULL, NULL, 'Adrián', 'Campos', 'Spanish');
INSERT INTO public.piloto VALUES (168, NULL, NULL, 'Jean-Louis', 'Schlesser', 'French');
INSERT INTO public.piloto VALUES (169, NULL, NULL, 'Pascal', 'Fabre', 'French');
INSERT INTO public.piloto VALUES (170, NULL, NULL, 'Teo', 'Fabi', 'Italian');
INSERT INTO public.piloto VALUES (171, NULL, NULL, 'Franco', 'Forini', 'Swiss');
INSERT INTO public.piloto VALUES (172, NULL, NULL, 'Jacques', 'Laffite', 'French');
INSERT INTO public.piloto VALUES (173, NULL, NULL, 'Elio', 'de Angelis', 'Italian');
INSERT INTO public.piloto VALUES (174, NULL, NULL, 'Johnny', 'Dumfries', 'British');
INSERT INTO public.piloto VALUES (175, NULL, NULL, 'Patrick', 'Tambay', 'French');
INSERT INTO public.piloto VALUES (176, NULL, NULL, 'Marc', 'Surer', 'Swiss');
INSERT INTO public.piloto VALUES (177, NULL, NULL, 'Keke', 'Rosberg', 'Finnish');
INSERT INTO public.piloto VALUES (178, NULL, NULL, 'Alan', 'Jones', 'Australian');
INSERT INTO public.piloto VALUES (179, NULL, NULL, 'Huub', 'Rothengatter', 'Dutch');
INSERT INTO public.piloto VALUES (180, NULL, NULL, 'Allen', 'Berg', 'Canadian');
INSERT INTO public.piloto VALUES (181, NULL, NULL, 'Manfred', 'Winkelhock', 'German');
INSERT INTO public.piloto VALUES (182, NULL, NULL, 'Niki', 'Lauda', 'Austrian');
INSERT INTO public.piloto VALUES (183, NULL, NULL, 'François', 'Hesnault', 'French');
INSERT INTO public.piloto VALUES (184, NULL, NULL, 'Mauro', 'Baldi', 'Italian');
INSERT INTO public.piloto VALUES (185, NULL, NULL, 'Stefan', 'Bellof', 'German');
INSERT INTO public.piloto VALUES (186, NULL, NULL, 'Kenny', 'Acheson', 'British');
INSERT INTO public.piloto VALUES (187, NULL, NULL, 'John', 'Watson', 'British');
INSERT INTO public.piloto VALUES (188, NULL, NULL, 'Johnny', 'Cecotto', 'Venezuelan');
INSERT INTO public.piloto VALUES (189, NULL, NULL, 'Jo', 'Gartner', 'Austrian');
INSERT INTO public.piloto VALUES (190, NULL, NULL, 'Corrado', 'Fabi', 'Italian');
INSERT INTO public.piloto VALUES (191, NULL, NULL, 'Mike', 'Thackwell', 'New Zealander');
INSERT INTO public.piloto VALUES (192, NULL, NULL, 'Chico', 'Serra', 'Brazilian');
INSERT INTO public.piloto VALUES (193, NULL, NULL, 'Danny', 'Sullivan', 'American');
INSERT INTO public.piloto VALUES (194, NULL, NULL, 'Eliseo', 'Salazar', 'Chilean');
INSERT INTO public.piloto VALUES (195, NULL, NULL, 'Roberto', 'Guerrero', 'Colombian');
INSERT INTO public.piloto VALUES (196, NULL, NULL, 'Raul', 'Boesel', 'Brazilian');
INSERT INTO public.piloto VALUES (197, NULL, NULL, 'Jean-Pierre', 'Jarier', 'French');
INSERT INTO public.piloto VALUES (198, NULL, NULL, 'Jacques', 'Villeneuve Sr.', 'Canadian');
INSERT INTO public.piloto VALUES (199, NULL, NULL, 'Carlos', 'Reutemann', 'Argentine');
INSERT INTO public.piloto VALUES (200, NULL, NULL, 'Jochen', 'Mass', 'German');
INSERT INTO public.piloto VALUES (201, NULL, NULL, 'Slim', 'Borgudd', 'Swedish');
INSERT INTO public.piloto VALUES (202, NULL, NULL, 'Didier', 'Pironi', 'French');
INSERT INTO public.piloto VALUES (203, NULL, NULL, 'Gilles', 'Villeneuve', 'Canadian');
INSERT INTO public.piloto VALUES (204, NULL, NULL, 'Riccardo', 'Paletti', 'Italian');
INSERT INTO public.piloto VALUES (205, NULL, NULL, 'Brian', 'Henton', 'British');
INSERT INTO public.piloto VALUES (206, NULL, NULL, 'Derek', 'Daly', 'Irish');
INSERT INTO public.piloto VALUES (207, NULL, NULL, 'Mario', 'Andretti', 'American');
INSERT INTO public.piloto VALUES (208, NULL, NULL, 'Emilio', 'de Villota', 'Spanish');
INSERT INTO public.piloto VALUES (209, NULL, NULL, 'Geoff', 'Lees', 'British');
INSERT INTO public.piloto VALUES (210, NULL, NULL, 'Tommy', 'Byrne', 'Irish');
INSERT INTO public.piloto VALUES (211, NULL, NULL, 'Rupert', 'Keegan', 'British');
INSERT INTO public.piloto VALUES (212, NULL, NULL, 'Hector', 'Rebaque', 'Mexican');
INSERT INTO public.piloto VALUES (213, NULL, NULL, 'Beppe', 'Gabbiani', 'Italian');
INSERT INTO public.piloto VALUES (214, NULL, NULL, 'Kevin', 'Cogan', 'American');
INSERT INTO public.piloto VALUES (215, NULL, NULL, 'Miguel Ángel', 'Guerra', 'Argentine');
INSERT INTO public.piloto VALUES (216, NULL, NULL, 'Siegfried', 'Stohr', 'Italian');
INSERT INTO public.piloto VALUES (217, NULL, NULL, 'Ricardo', 'Zunino', 'Argentine');
INSERT INTO public.piloto VALUES (218, NULL, NULL, 'Ricardo', 'Londoño', 'Colombian');
INSERT INTO public.piloto VALUES (219, NULL, NULL, 'Jean-Pierre', 'Jabouille', 'French');
INSERT INTO public.piloto VALUES (220, NULL, NULL, 'Giorgio', 'Francia', 'Italian');
INSERT INTO public.piloto VALUES (221, NULL, NULL, 'Patrick', 'Depailler', 'French');
INSERT INTO public.piloto VALUES (222, NULL, NULL, 'Jody', 'Scheckter', 'South African');
INSERT INTO public.piloto VALUES (223, NULL, NULL, 'Clay', 'Regazzoni', 'Swiss');
INSERT INTO public.piloto VALUES (224, NULL, NULL, 'Emerson', 'Fittipaldi', 'Brazilian');
INSERT INTO public.piloto VALUES (225, NULL, NULL, 'Dave', 'Kennedy', 'Irish');
INSERT INTO public.piloto VALUES (226, NULL, NULL, 'Stephen', 'South', 'British');
INSERT INTO public.piloto VALUES (227, NULL, NULL, 'Tiff', 'Needell', 'British');
INSERT INTO public.piloto VALUES (228, NULL, NULL, 'Desiré', 'Wilson', 'South African');
INSERT INTO public.piloto VALUES (229, NULL, NULL, 'Harald', 'Ertl', 'Austrian');
INSERT INTO public.piloto VALUES (230, NULL, NULL, 'Vittorio', 'Brambilla', 'Italian');
INSERT INTO public.piloto VALUES (231, NULL, NULL, 'James', 'Hunt', 'British');
INSERT INTO public.piloto VALUES (232, NULL, NULL, 'Arturo', 'Merzario', 'Italian');
INSERT INTO public.piloto VALUES (233, NULL, NULL, 'Hans-Joachim', 'Stuck', 'German');
INSERT INTO public.piloto VALUES (234, NULL, NULL, 'Gianfranco', 'Brancatelli', 'Italian');
INSERT INTO public.piloto VALUES (235, NULL, NULL, 'Jacky', 'Ickx', 'Belgian');
INSERT INTO public.piloto VALUES (236, NULL, NULL, 'Patrick', 'Gaillard', 'French');
INSERT INTO public.piloto VALUES (237, NULL, NULL, 'Alex', 'Ribeiro', 'Brazilian');
INSERT INTO public.piloto VALUES (238, NULL, NULL, 'Ronnie', 'Peterson', 'Swedish');
INSERT INTO public.piloto VALUES (239, NULL, NULL, 'Brett', 'Lunger', 'American');
INSERT INTO public.piloto VALUES (240, NULL, NULL, 'Danny', 'Ongais', 'American');
INSERT INTO public.piloto VALUES (241, NULL, NULL, 'Lamberto', 'Leoni', 'Italian');
INSERT INTO public.piloto VALUES (242, NULL, NULL, 'Divina', 'Galica', 'British');
INSERT INTO public.piloto VALUES (243, NULL, NULL, 'Rolf', 'Stommelen', 'German');
INSERT INTO public.piloto VALUES (244, NULL, NULL, 'Alberto', 'Colombo', 'Italian');
INSERT INTO public.piloto VALUES (245, NULL, NULL, 'Tony', 'Trimmer', 'British');
INSERT INTO public.piloto VALUES (246, NULL, NULL, 'Hans', 'Binder', 'Austrian');
INSERT INTO public.piloto VALUES (247, NULL, NULL, 'Michael', 'Bleekemolen', 'Dutch');
INSERT INTO public.piloto VALUES (248, NULL, NULL, 'Carlo', 'Franchi', 'Italian');
INSERT INTO public.piloto VALUES (249, NULL, NULL, 'Bobby', 'Rahal', 'American');
INSERT INTO public.piloto VALUES (250, NULL, NULL, 'Carlos', 'Pace', 'Brazilian');
INSERT INTO public.piloto VALUES (251, NULL, NULL, 'Ian', 'Scheckter', 'South African');
INSERT INTO public.piloto VALUES (252, NULL, NULL, 'Tom', 'Pryce', 'British');
INSERT INTO public.piloto VALUES (253, NULL, NULL, 'Ingo', 'Hoffmann', 'Brazilian');
INSERT INTO public.piloto VALUES (254, NULL, NULL, 'Renzo', 'Zorzi', 'Italian');
INSERT INTO public.piloto VALUES (255, NULL, NULL, 'Gunnar', 'Nilsson', 'Swedish');
INSERT INTO public.piloto VALUES (256, NULL, NULL, 'Larry', 'Perkins', 'Australian');
INSERT INTO public.piloto VALUES (257, NULL, NULL, 'Boy', 'Lunger', 'Dutch');
INSERT INTO public.piloto VALUES (258, NULL, NULL, 'Patrick', 'Nève', 'Belgian');
INSERT INTO public.piloto VALUES (259, NULL, NULL, 'David', 'Purley', 'British');
INSERT INTO public.piloto VALUES (260, NULL, NULL, 'Conny', 'Andersson', 'Swedish');
INSERT INTO public.piloto VALUES (261, NULL, NULL, 'Bernard', 'de Dryver', 'Belgian');
INSERT INTO public.piloto VALUES (262, NULL, NULL, 'Jackie', 'Oliver', 'British');
INSERT INTO public.piloto VALUES (263, NULL, NULL, 'Mikko', 'Kozarowitzky', 'Finnish');
INSERT INTO public.piloto VALUES (264, NULL, NULL, 'Andy', 'Sutcliffe', 'British');
INSERT INTO public.piloto VALUES (265, NULL, NULL, 'Guy', 'Edwards', 'British');
INSERT INTO public.piloto VALUES (266, NULL, NULL, 'Brian', 'McGuire', 'Australian');
INSERT INTO public.piloto VALUES (267, NULL, NULL, 'Vern', 'Schuppan', 'Australian');
INSERT INTO public.piloto VALUES (268, NULL, NULL, 'Hans', 'Heyer', 'German');
INSERT INTO public.piloto VALUES (269, NULL, NULL, 'Teddy', 'Pilette', 'Belgian');
INSERT INTO public.piloto VALUES (270, NULL, NULL, 'Ian', 'Ashley', 'British');
INSERT INTO public.piloto VALUES (271, NULL, NULL, 'Loris', 'Kessel', 'Swiss');
INSERT INTO public.piloto VALUES (272, NULL, NULL, 'Kunimitsu', 'Takahashi', 'Japanese');
INSERT INTO public.piloto VALUES (273, NULL, NULL, 'Kazuyoshi', 'Hoshino', 'Japanese');
INSERT INTO public.piloto VALUES (274, NULL, NULL, 'Noritake', 'Takahara', 'Japanese');
INSERT INTO public.piloto VALUES (275, NULL, NULL, 'Lella', 'Lombardi', 'Italian');
INSERT INTO public.piloto VALUES (276, NULL, NULL, 'Bob', 'Evans', 'British');
INSERT INTO public.piloto VALUES (277, NULL, NULL, 'Michel', 'Leclère', 'French');
INSERT INTO public.piloto VALUES (278, NULL, NULL, 'Chris', 'Amon', 'New Zealander');
INSERT INTO public.piloto VALUES (279, NULL, NULL, 'Emilio', 'Zapico', 'Spanish');
INSERT INTO public.piloto VALUES (280, NULL, NULL, 'Henri', 'Pescarolo', 'French');
INSERT INTO public.piloto VALUES (281, NULL, NULL, 'Jac', 'Nelleman', 'Danish');
INSERT INTO public.piloto VALUES (282, NULL, NULL, 'Damien', 'Magee', 'British');
INSERT INTO public.piloto VALUES (283, NULL, NULL, 'Mike', 'Wilds', 'British');
INSERT INTO public.piloto VALUES (284, NULL, NULL, 'Alessandro', 'Pesenti-Rossi', 'Italian');
INSERT INTO public.piloto VALUES (285, NULL, NULL, 'Otto', 'Stuppacher', 'Austrian');
INSERT INTO public.piloto VALUES (286, NULL, NULL, 'Warwick', 'Brown', 'Australian');
INSERT INTO public.piloto VALUES (287, NULL, NULL, 'Masahiro', 'Hasemi', 'Japanese');
INSERT INTO public.piloto VALUES (288, NULL, NULL, 'Mark', 'Donohue', 'American');
INSERT INTO public.piloto VALUES (289, NULL, NULL, 'Graham', 'Hill', 'British');
INSERT INTO public.piloto VALUES (290, NULL, NULL, 'Wilson', 'Fittipaldi', 'Brazilian');
INSERT INTO public.piloto VALUES (291, NULL, NULL, 'Guy', 'Tunmer', 'South African');
INSERT INTO public.piloto VALUES (292, NULL, NULL, 'Eddie', 'Keizan', 'South African');
INSERT INTO public.piloto VALUES (293, NULL, NULL, 'Dave', 'Charlton', 'South African');
INSERT INTO public.piloto VALUES (294, NULL, NULL, 'Tony', 'Brise', 'British');
INSERT INTO public.piloto VALUES (295, NULL, NULL, 'Roelof', 'Wunderink', 'Dutch');
INSERT INTO public.piloto VALUES (296, NULL, NULL, 'François', 'Migault', 'French');
INSERT INTO public.piloto VALUES (297, NULL, NULL, 'Torsten', 'Palm', 'Swedish');
INSERT INTO public.piloto VALUES (298, NULL, NULL, 'Gijs', 'van Lennep', 'Dutch');
INSERT INTO public.piloto VALUES (299, NULL, NULL, 'Hiroshi', 'Fushida', 'Japanese');
INSERT INTO public.piloto VALUES (300, NULL, NULL, 'John', 'Nicholson', 'New Zealander');
INSERT INTO public.piloto VALUES (301, NULL, NULL, 'Dave', 'Morgan', 'British');
INSERT INTO public.piloto VALUES (302, NULL, NULL, 'Jim', 'Crawford', 'British');
INSERT INTO public.piloto VALUES (303, NULL, NULL, 'Jo', 'Vonlanthen', 'Swiss');
INSERT INTO public.piloto VALUES (304, NULL, NULL, 'Denny', 'Hulme', 'New Zealander');
INSERT INTO public.piloto VALUES (305, NULL, NULL, 'Mike', 'Hailwood', 'British');
INSERT INTO public.piloto VALUES (306, NULL, NULL, 'Jean-Pierre', 'Beltoise', 'French');
INSERT INTO public.piloto VALUES (307, NULL, NULL, 'Howden', 'Ganley', 'New Zealander');
INSERT INTO public.piloto VALUES (308, NULL, NULL, 'Richard', 'Robarts', 'British');
INSERT INTO public.piloto VALUES (309, NULL, NULL, 'Peter', 'Revson', 'American');
INSERT INTO public.piloto VALUES (310, NULL, NULL, 'Paddy', 'Driver', 'South African');
INSERT INTO public.piloto VALUES (311, NULL, NULL, 'Tom', 'Belsø', 'Danish');
INSERT INTO public.piloto VALUES (312, NULL, NULL, 'Brian', 'Redman', 'British');
INSERT INTO public.piloto VALUES (313, NULL, NULL, 'Rikky', 'von Opel', 'Liechtensteiner');
INSERT INTO public.piloto VALUES (314, NULL, NULL, 'Tim', 'Schenken', 'Australian');
INSERT INTO public.piloto VALUES (315, NULL, NULL, 'Gérard', 'Larrousse', 'French');
INSERT INTO public.piloto VALUES (316, NULL, NULL, 'Leo', 'Kinnunen', 'Finnish');
INSERT INTO public.piloto VALUES (317, NULL, NULL, 'Reine', 'Wisell', 'Swedish');
INSERT INTO public.piloto VALUES (318, NULL, NULL, 'Bertil', 'Roos', 'Swedish');
INSERT INTO public.piloto VALUES (319, NULL, NULL, 'José', 'Dolhem', 'French');
INSERT INTO public.piloto VALUES (320, NULL, NULL, 'Peter', 'Gethin', 'British');
INSERT INTO public.piloto VALUES (321, NULL, NULL, 'Derek', 'Bell', 'British');
INSERT INTO public.piloto VALUES (322, NULL, NULL, 'David', 'Hobbs', 'British');
INSERT INTO public.piloto VALUES (323, NULL, NULL, 'Dieter', 'Quester', 'Austrian');
INSERT INTO public.piloto VALUES (324, NULL, NULL, 'Helmuth', 'Koinigg', 'Austrian');
INSERT INTO public.piloto VALUES (325, NULL, NULL, 'Carlo', 'Facetti', 'Italian');
INSERT INTO public.piloto VALUES (326, NULL, NULL, 'Eppie', 'Wietzes', 'Canadian');
INSERT INTO public.piloto VALUES (327, NULL, NULL, 'François', 'Cevert', 'French');
INSERT INTO public.piloto VALUES (328, NULL, NULL, 'Jackie', 'Stewart', 'British');
INSERT INTO public.piloto VALUES (329, NULL, NULL, 'Mike', 'Beuttler', 'British');
INSERT INTO public.piloto VALUES (330, NULL, NULL, 'Nanni', 'Galli', 'Italian');
INSERT INTO public.piloto VALUES (331, NULL, NULL, 'Luiz', 'Bueno', 'Brazilian');
INSERT INTO public.piloto VALUES (332, NULL, NULL, 'George', 'Follmer', 'American');
INSERT INTO public.piloto VALUES (333, NULL, NULL, 'Andrea', 'de Adamich', 'Italian');
INSERT INTO public.piloto VALUES (334, NULL, NULL, 'Jackie', 'Pretorius', 'South African');
INSERT INTO public.piloto VALUES (335, NULL, NULL, 'Roger', 'Williamson', 'British');
INSERT INTO public.piloto VALUES (336, NULL, NULL, 'Graham', 'McRae', 'New Zealander');
INSERT INTO public.piloto VALUES (337, NULL, NULL, 'Helmut', 'Marko', 'Austrian');
INSERT INTO public.piloto VALUES (338, NULL, NULL, 'David', 'Walker', 'Australian');
INSERT INTO public.piloto VALUES (339, NULL, NULL, 'Alex', 'Soler-Roig', 'Spanish');
INSERT INTO public.piloto VALUES (340, NULL, NULL, 'John', 'Love', 'Rhodesian');
INSERT INTO public.piloto VALUES (341, NULL, NULL, 'John', 'Surtees', 'British');
INSERT INTO public.piloto VALUES (342, NULL, NULL, 'Skip', 'Barber', 'American');
INSERT INTO public.piloto VALUES (343, NULL, NULL, 'Bill', 'Brack', 'Canadian');
INSERT INTO public.piloto VALUES (344, NULL, NULL, 'Sam', 'Posey', 'American');
INSERT INTO public.piloto VALUES (345, NULL, NULL, 'Pedro', 'Rodríguez', 'Mexican');
INSERT INTO public.piloto VALUES (346, NULL, NULL, 'Jo', 'Siffert', 'Swiss');
INSERT INTO public.piloto VALUES (347, NULL, NULL, 'Jo', 'Bonnier', 'Swedish');
INSERT INTO public.piloto VALUES (348, NULL, NULL, 'François', 'Mazet', 'French');
INSERT INTO public.piloto VALUES (349, NULL, NULL, 'Max', 'Jean', 'French');
INSERT INTO public.piloto VALUES (350, NULL, NULL, 'Vic', 'Elford', 'British');
INSERT INTO public.piloto VALUES (351, NULL, NULL, 'Silvio', 'Moser', 'Swiss');
INSERT INTO public.piloto VALUES (352, NULL, NULL, 'George', 'Eaton', 'Canadian');
INSERT INTO public.piloto VALUES (353, NULL, NULL, 'Pete', 'Lovely', 'American');
INSERT INTO public.piloto VALUES (354, NULL, NULL, 'Chris', 'Craft', 'British');
INSERT INTO public.piloto VALUES (355, NULL, NULL, 'John', 'Cannon', 'Canadian');
INSERT INTO public.piloto VALUES (356, NULL, NULL, 'Jack', 'Brabham', 'Australian');
INSERT INTO public.piloto VALUES (357, NULL, NULL, 'John', 'Miles', 'British');
INSERT INTO public.piloto VALUES (358, NULL, NULL, 'Jochen', 'Rindt', 'Austrian');
INSERT INTO public.piloto VALUES (359, NULL, NULL, 'Johnny', 'Servoz-Gavin', 'French');
INSERT INTO public.piloto VALUES (360, NULL, NULL, 'Bruce', 'McLaren', 'New Zealander');
INSERT INTO public.piloto VALUES (361, NULL, NULL, 'Piers', 'Courage', 'British');
INSERT INTO public.piloto VALUES (362, NULL, NULL, 'Peter', 'de Klerk', 'South African');
INSERT INTO public.piloto VALUES (363, NULL, NULL, 'Ignazio', 'Giunti', 'Italian');
INSERT INTO public.piloto VALUES (364, NULL, NULL, 'Dan', 'Gurney', 'American');
INSERT INTO public.piloto VALUES (365, NULL, NULL, 'Hubert', 'Hahne', 'German');
INSERT INTO public.piloto VALUES (366, NULL, NULL, 'Gus', 'Hutchison', 'American');
INSERT INTO public.piloto VALUES (367, NULL, NULL, 'Peter', 'Westbury', 'British');
INSERT INTO public.piloto VALUES (368, NULL, NULL, 'Sam', 'Tingle', 'Rhodesian');
INSERT INTO public.piloto VALUES (369, NULL, NULL, 'Basil', 'van Rooyen', 'South African');
INSERT INTO public.piloto VALUES (370, NULL, NULL, 'Richard', 'Attwood', 'British');
INSERT INTO public.piloto VALUES (371, NULL, NULL, 'Al', 'Pease', 'Canadian');
INSERT INTO public.piloto VALUES (372, NULL, NULL, 'John', 'Cordts', 'Canadian');
INSERT INTO public.piloto VALUES (373, NULL, NULL, 'Jim', 'Clark', 'British');
INSERT INTO public.piloto VALUES (374, NULL, NULL, 'Mike', 'Spence', 'British');
INSERT INTO public.piloto VALUES (375, NULL, NULL, 'Ludovico', 'Scarfiotti', 'Italian');
INSERT INTO public.piloto VALUES (376, NULL, 'BIA', 'Lucien', 'Bianchi', 'Belgian');
INSERT INTO public.piloto VALUES (377, NULL, NULL, 'Jo', 'Schlesser', 'French');
INSERT INTO public.piloto VALUES (378, NULL, NULL, 'Robin', 'Widdows', 'British');
INSERT INTO public.piloto VALUES (379, NULL, NULL, 'Kurt', 'Ahrens', 'German');
INSERT INTO public.piloto VALUES (380, NULL, NULL, 'Frank', 'Gardner', 'Australian');
INSERT INTO public.piloto VALUES (381, NULL, NULL, 'Bobby', 'Unser', 'American');
INSERT INTO public.piloto VALUES (382, NULL, NULL, 'Moisés', 'Solana', 'Mexican');
INSERT INTO public.piloto VALUES (383, NULL, NULL, 'Bob', 'Anderson', 'British');
INSERT INTO public.piloto VALUES (384, NULL, NULL, 'Luki', 'Botha', 'South African');
INSERT INTO public.piloto VALUES (385, NULL, NULL, 'Lorenzo', 'Bandini', 'Italian');
INSERT INTO public.piloto VALUES (386, NULL, NULL, 'Richie', 'Ginther', 'American');
INSERT INTO public.piloto VALUES (387, NULL, NULL, 'Mike', 'Parkes', 'British');
INSERT INTO public.piloto VALUES (388, NULL, NULL, 'Chris', 'Irwin', 'British');
INSERT INTO public.piloto VALUES (389, NULL, NULL, 'Guy', 'Ligier', 'French');
INSERT INTO public.piloto VALUES (390, NULL, NULL, 'Alan', 'Rees', 'British');
INSERT INTO public.piloto VALUES (391, NULL, NULL, 'Brian', 'Hart', 'British');
INSERT INTO public.piloto VALUES (392, NULL, NULL, 'Mike', 'Fisher', 'American');
INSERT INTO public.piloto VALUES (393, NULL, NULL, 'Tom', 'Jones', 'American');
INSERT INTO public.piloto VALUES (394, NULL, NULL, 'Giancarlo', 'Baghetti', 'Italian');
INSERT INTO public.piloto VALUES (395, NULL, NULL, 'Jonathan', 'Williams', 'British');
INSERT INTO public.piloto VALUES (396, NULL, NULL, 'Bob', 'Bondurant', 'American');
INSERT INTO public.piloto VALUES (397, NULL, NULL, 'Peter', 'Arundell', 'British');
INSERT INTO public.piloto VALUES (398, NULL, NULL, 'Vic', 'Wilson', 'British');
INSERT INTO public.piloto VALUES (399, NULL, NULL, 'John', 'Taylor', 'British');
INSERT INTO public.piloto VALUES (400, NULL, NULL, 'Chris', 'Lawrence', 'British');
INSERT INTO public.piloto VALUES (401, NULL, NULL, 'Trevor', 'Taylor', 'British');
INSERT INTO public.piloto VALUES (402, NULL, NULL, 'Giacomo', 'Russo', 'Italian');
INSERT INTO public.piloto VALUES (403, NULL, NULL, 'Phil', 'Hill', 'American');
INSERT INTO public.piloto VALUES (404, NULL, NULL, 'Innes', 'Ireland', 'British');
INSERT INTO public.piloto VALUES (405, NULL, NULL, 'Ronnie', 'Bucknum', 'American');
INSERT INTO public.piloto VALUES (406, NULL, NULL, 'Paul', 'Hawkins', 'Australian');
INSERT INTO public.piloto VALUES (407, NULL, NULL, 'David', 'Prophet', 'British');
INSERT INTO public.piloto VALUES (408, NULL, NULL, 'Tony', 'Maggs', 'South African');
INSERT INTO public.piloto VALUES (409, NULL, NULL, 'Trevor', 'Blokdyk', 'South African');
INSERT INTO public.piloto VALUES (410, NULL, NULL, 'Neville', 'Lederle', 'South African');
INSERT INTO public.piloto VALUES (411, NULL, NULL, 'Doug', 'Serrurier', 'South African');
INSERT INTO public.piloto VALUES (412, NULL, NULL, 'Brausch', 'Niemann', 'South African');
INSERT INTO public.piloto VALUES (413, NULL, NULL, 'Ernie', 'Pieterse', 'South African');
INSERT INTO public.piloto VALUES (414, NULL, NULL, 'Clive', 'Puzey', 'Rhodesian');
INSERT INTO public.piloto VALUES (415, NULL, NULL, 'Ray', 'Reed', 'South African');
INSERT INTO public.piloto VALUES (416, NULL, NULL, 'David', 'Clapham', 'South African');
INSERT INTO public.piloto VALUES (417, NULL, NULL, 'Alex', 'Blignaut', 'South African');
INSERT INTO public.piloto VALUES (418, NULL, NULL, 'Masten', 'Gregory', 'American');
INSERT INTO public.piloto VALUES (419, NULL, NULL, 'John', 'Rhodes', 'British');
INSERT INTO public.piloto VALUES (420, NULL, NULL, 'Ian', 'Raby', 'British');
INSERT INTO public.piloto VALUES (421, NULL, NULL, 'Alan', 'Rollinson', 'British');
INSERT INTO public.piloto VALUES (422, NULL, NULL, 'Brian', 'Gubby', 'British');
INSERT INTO public.piloto VALUES (423, NULL, NULL, 'Gerhard', 'Mitter', 'German');
INSERT INTO public.piloto VALUES (424, NULL, NULL, 'Roberto', 'Bussinello', 'Italian');
INSERT INTO public.piloto VALUES (425, NULL, NULL, 'Nino', 'Vaccarella', 'Italian');
INSERT INTO public.piloto VALUES (426, NULL, NULL, 'Giorgio', 'Bassi', 'Italian');
INSERT INTO public.piloto VALUES (427, NULL, NULL, 'Maurice', 'Trintignant', 'French');
INSERT INTO public.piloto VALUES (428, NULL, NULL, 'Bernard', 'Collomb', 'French');
INSERT INTO public.piloto VALUES (429, NULL, NULL, 'André', 'Pilette', 'Belgian');
INSERT INTO public.piloto VALUES (430, NULL, NULL, 'Carel Godin', 'de Beaufort', 'Dutch');
INSERT INTO public.piloto VALUES (431, NULL, NULL, 'Edgar', 'Barth', 'German');
INSERT INTO public.piloto VALUES (432, NULL, NULL, 'Mário de Araújo', 'Cabral', 'Portuguese');
INSERT INTO public.piloto VALUES (433, NULL, NULL, 'Walt', 'Hansgen', 'American');
INSERT INTO public.piloto VALUES (434, NULL, NULL, 'Hap', 'Sharp', 'American');
INSERT INTO public.piloto VALUES (435, NULL, NULL, 'Willy', 'Mairesse', 'Belgian');
INSERT INTO public.piloto VALUES (436, NULL, NULL, 'John', 'Campbell-Jones', 'British');
INSERT INTO public.piloto VALUES (437, NULL, NULL, 'Ian', 'Burgess', 'British');
INSERT INTO public.piloto VALUES (438, NULL, NULL, 'Tony', 'Settember', 'American');
INSERT INTO public.piloto VALUES (439, NULL, NULL, 'Nasif', 'Estéfano', 'Argentine');
INSERT INTO public.piloto VALUES (440, NULL, NULL, 'Jim', 'Hall', 'American');
INSERT INTO public.piloto VALUES (441, NULL, NULL, 'Tim', 'Parnell', 'British');
INSERT INTO public.piloto VALUES (442, NULL, NULL, 'Kurt', 'Kuhnke', 'German');
INSERT INTO public.piloto VALUES (443, NULL, NULL, 'Ernesto', 'Brambilla', 'Italian');
INSERT INTO public.piloto VALUES (444, NULL, NULL, 'Roberto', 'Lippi', 'Italian');
INSERT INTO public.piloto VALUES (445, NULL, NULL, 'Günther', 'Seiffert', 'German');
INSERT INTO public.piloto VALUES (446, NULL, NULL, 'Carlo', 'Abate', 'Italian');
INSERT INTO public.piloto VALUES (447, NULL, NULL, 'Gaetano', 'Starrabba', 'Italian');
INSERT INTO public.piloto VALUES (448, NULL, NULL, 'Peter', 'Broeker', 'Canadian');
INSERT INTO public.piloto VALUES (449, NULL, NULL, 'Rodger', 'Ward', 'American');
INSERT INTO public.piloto VALUES (450, NULL, NULL, 'Ernie', 'de Vos', 'Dutch');
INSERT INTO public.piloto VALUES (451, NULL, NULL, 'Frank', 'Dochnal', 'American');
INSERT INTO public.piloto VALUES (452, NULL, NULL, 'Thomas', 'Monarch', 'American');
INSERT INTO public.piloto VALUES (842, 10, 'GAS', 'Pierre', 'Gasly', 'French');
INSERT INTO public.piloto VALUES (453, NULL, NULL, 'Jackie', 'Lewis', 'British');
INSERT INTO public.piloto VALUES (454, NULL, NULL, 'Ricardo', 'Rodríguez', 'Mexican');
INSERT INTO public.piloto VALUES (455, NULL, NULL, 'Wolfgang', 'Seidel', 'German');
INSERT INTO public.piloto VALUES (456, NULL, NULL, 'Roy', 'Salvadori', 'British');
INSERT INTO public.piloto VALUES (457, NULL, NULL, 'Ben', 'Pon', 'Dutch');
INSERT INTO public.piloto VALUES (458, NULL, NULL, 'Rob', 'Slotemaker', 'Dutch');
INSERT INTO public.piloto VALUES (459, NULL, NULL, 'Tony', 'Marsh', 'British');
INSERT INTO public.piloto VALUES (460, NULL, NULL, 'Gerry', 'Ashmore', 'British');
INSERT INTO public.piloto VALUES (461, NULL, NULL, 'Heinz', 'Schiller', 'Swiss');
INSERT INTO public.piloto VALUES (462, NULL, NULL, 'Colin', 'Davis', 'British');
INSERT INTO public.piloto VALUES (463, NULL, NULL, 'Jay', 'Chamberlain', 'American');
INSERT INTO public.piloto VALUES (464, NULL, NULL, 'Tony', 'Shelly', 'New Zealander');
INSERT INTO public.piloto VALUES (465, NULL, NULL, 'Keith', 'Greene', 'British');
INSERT INTO public.piloto VALUES (466, NULL, NULL, 'Heini', 'Walter', 'Swiss');
INSERT INTO public.piloto VALUES (467, NULL, NULL, 'Ernesto', 'Prinoth', 'Italian');
INSERT INTO public.piloto VALUES (468, NULL, NULL, 'Roger', 'Penske', 'American');
INSERT INTO public.piloto VALUES (469, NULL, NULL, 'Rob', 'Schroeder', 'British');
INSERT INTO public.piloto VALUES (470, NULL, NULL, 'Timmy', 'Mayer', 'American');
INSERT INTO public.piloto VALUES (471, NULL, NULL, 'Bruce', 'Johnstone', 'South African');
INSERT INTO public.piloto VALUES (472, NULL, NULL, 'Mike', 'Harris', 'South African');
INSERT INTO public.piloto VALUES (473, NULL, NULL, 'Gary', 'Hocking', 'Rhodesian');
INSERT INTO public.piloto VALUES (474, NULL, NULL, 'Syd', 'van der Vyver', 'South African');
INSERT INTO public.piloto VALUES (475, NULL, NULL, 'Stirling', 'Moss', 'British');
INSERT INTO public.piloto VALUES (476, NULL, NULL, 'Wolfgang', 'von Trips', 'German');
INSERT INTO public.piloto VALUES (477, NULL, NULL, 'Cliff', 'Allison', 'British');
INSERT INTO public.piloto VALUES (478, NULL, NULL, 'Hans', 'Herrmann', 'German');
INSERT INTO public.piloto VALUES (479, NULL, NULL, 'Tony', 'Brooks', 'British');
INSERT INTO public.piloto VALUES (480, NULL, NULL, 'Michael', 'May', 'Swiss');
INSERT INTO public.piloto VALUES (481, NULL, NULL, 'Henry', 'Taylor', 'British');
INSERT INTO public.piloto VALUES (482, NULL, NULL, 'Olivier', 'Gendebien', 'Belgian');
INSERT INTO public.piloto VALUES (483, NULL, NULL, 'Giorgio', 'Scarlatti', 'Italian');
INSERT INTO public.piloto VALUES (484, NULL, NULL, 'Brian', 'Naylor', 'British');
INSERT INTO public.piloto VALUES (485, NULL, NULL, 'Juan Manuel', 'Bordeu', 'Argentine');
INSERT INTO public.piloto VALUES (486, NULL, NULL, 'Jack', 'Fairman', 'British');
INSERT INTO public.piloto VALUES (487, NULL, NULL, 'Massimo', 'Natili', 'Italian');
INSERT INTO public.piloto VALUES (488, NULL, NULL, 'Peter', 'Monteverdi', 'Swiss');
INSERT INTO public.piloto VALUES (489, NULL, NULL, 'Renato', 'Pirocchi', 'Italian');
INSERT INTO public.piloto VALUES (490, NULL, NULL, 'Geoff', 'Duke', 'British');
INSERT INTO public.piloto VALUES (491, NULL, NULL, 'Alfonso', 'Thiele', 'American-Italian');
INSERT INTO public.piloto VALUES (492, NULL, NULL, 'Menato', 'Boffa', 'Italian');
INSERT INTO public.piloto VALUES (493, NULL, NULL, 'Peter', 'Ryan', 'Canadian');
INSERT INTO public.piloto VALUES (494, NULL, NULL, 'Lloyd', 'Ruby', 'American');
INSERT INTO public.piloto VALUES (495, NULL, NULL, 'Ken', 'Miles', 'British');
INSERT INTO public.piloto VALUES (496, NULL, NULL, 'Carlos', 'Menditeguy', 'Argentine');
INSERT INTO public.piloto VALUES (497, NULL, NULL, 'Alberto Rodriguez', 'Larreta', 'Argentine');
INSERT INTO public.piloto VALUES (498, NULL, NULL, 'José Froilán', 'González', 'Argentine');
INSERT INTO public.piloto VALUES (499, NULL, NULL, 'Roberto', 'Bonomi', 'Argentine');
INSERT INTO public.piloto VALUES (500, NULL, NULL, 'Gino', 'Munaron', 'Italian');
INSERT INTO public.piloto VALUES (501, NULL, NULL, 'Harry', 'Schell', 'American');
INSERT INTO public.piloto VALUES (502, NULL, NULL, 'Alan', 'Stacey', 'British');
INSERT INTO public.piloto VALUES (503, NULL, NULL, 'Ettore', 'Chimeri', 'Venezuelan');
INSERT INTO public.piloto VALUES (504, NULL, NULL, 'Antonio', 'Creus', 'Spanish');
INSERT INTO public.piloto VALUES (505, NULL, NULL, 'Chris', 'Bristow', 'British');
INSERT INTO public.piloto VALUES (506, NULL, NULL, 'Bruce', 'Halford', 'British');
INSERT INTO public.piloto VALUES (507, NULL, NULL, 'Chuck', 'Daigh', 'American');
INSERT INTO public.piloto VALUES (508, NULL, NULL, 'Lance', 'Reventlow', 'American');
INSERT INTO public.piloto VALUES (509, NULL, NULL, 'Jim', 'Rathmann', 'American');
INSERT INTO public.piloto VALUES (510, NULL, NULL, 'Paul', 'Goldsmith', 'American');
INSERT INTO public.piloto VALUES (511, NULL, NULL, 'Don', 'Branson', 'American');
INSERT INTO public.piloto VALUES (512, NULL, NULL, 'Johnny', 'Thomson', 'American');
INSERT INTO public.piloto VALUES (513, NULL, NULL, 'Eddie', 'Johnson', 'American');
INSERT INTO public.piloto VALUES (514, NULL, NULL, 'Bob', 'Veith', 'American');
INSERT INTO public.piloto VALUES (515, NULL, NULL, 'Bud', 'Tingelstad', 'American');
INSERT INTO public.piloto VALUES (516, NULL, NULL, 'Bob', 'Christie', 'American');
INSERT INTO public.piloto VALUES (517, NULL, NULL, 'Red', 'Amick', 'American');
INSERT INTO public.piloto VALUES (518, NULL, NULL, 'Duane', 'Carter', 'American');
INSERT INTO public.piloto VALUES (519, NULL, NULL, 'Bill', 'Homeier', 'American');
INSERT INTO public.piloto VALUES (520, NULL, NULL, 'Gene', 'Hartley', 'American');
INSERT INTO public.piloto VALUES (521, NULL, NULL, 'Chuck', 'Stevenson', 'American');
INSERT INTO public.piloto VALUES (522, NULL, NULL, 'Bobby', 'Grim', 'American');
INSERT INTO public.piloto VALUES (523, NULL, NULL, 'Shorty', 'Templeman', 'American');
INSERT INTO public.piloto VALUES (524, NULL, NULL, 'Jim', 'Hurtubise', 'American');
INSERT INTO public.piloto VALUES (525, NULL, NULL, 'Jimmy', 'Bryan', 'American');
INSERT INTO public.piloto VALUES (526, NULL, NULL, 'Troy', 'Ruttman', 'American');
INSERT INTO public.piloto VALUES (527, NULL, NULL, 'Eddie', 'Sachs', 'American');
INSERT INTO public.piloto VALUES (528, NULL, NULL, 'Don', 'Freeland', 'American');
INSERT INTO public.piloto VALUES (529, NULL, NULL, 'Tony', 'Bettenhausen', 'American');
INSERT INTO public.piloto VALUES (530, NULL, NULL, 'Wayne', 'Weiler', 'American');
INSERT INTO public.piloto VALUES (531, NULL, NULL, 'Anthony', 'Foyt', 'American');
INSERT INTO public.piloto VALUES (532, NULL, NULL, 'Eddie', 'Russo', 'American');
INSERT INTO public.piloto VALUES (533, NULL, NULL, 'Johnny', 'Boyd', 'American');
INSERT INTO public.piloto VALUES (534, NULL, NULL, 'Gene', 'Force', 'American');
INSERT INTO public.piloto VALUES (535, NULL, NULL, 'Jim', 'McWithey', 'American');
INSERT INTO public.piloto VALUES (536, NULL, NULL, 'Len', 'Sutton', 'American');
INSERT INTO public.piloto VALUES (537, NULL, NULL, 'Dick', 'Rathmann', 'American');
INSERT INTO public.piloto VALUES (538, NULL, NULL, 'Al', 'Herman', 'American');
INSERT INTO public.piloto VALUES (539, NULL, NULL, 'Dempsey', 'Wilson', 'American');
INSERT INTO public.piloto VALUES (540, NULL, NULL, 'Mike', 'Taylor', 'British');
INSERT INTO public.piloto VALUES (541, NULL, NULL, 'Ron', 'Flockhart', 'British');
INSERT INTO public.piloto VALUES (542, NULL, NULL, 'David', 'Piper', 'British');
INSERT INTO public.piloto VALUES (543, NULL, NULL, 'Giulio', 'Cabianca', 'Italian');
INSERT INTO public.piloto VALUES (544, NULL, NULL, 'Piero', 'Drogo', 'Italian');
INSERT INTO public.piloto VALUES (545, NULL, NULL, 'Fred', 'Gamble', 'American');
INSERT INTO public.piloto VALUES (546, NULL, NULL, 'Arthur', 'Owen', 'British');
INSERT INTO public.piloto VALUES (547, NULL, NULL, 'Horace', 'Gould', 'British');
INSERT INTO public.piloto VALUES (548, NULL, NULL, 'Bob', 'Drake', 'American');
INSERT INTO public.piloto VALUES (549, NULL, NULL, 'Ivor', 'Bueb', 'British');
INSERT INTO public.piloto VALUES (550, NULL, NULL, 'Alain', 'de Changy', 'Belgian');
INSERT INTO public.piloto VALUES (551, NULL, NULL, 'Maria', 'de Filippis', 'Italian');
INSERT INTO public.piloto VALUES (552, NULL, NULL, 'Jean', 'Lucienbonnet', 'French');
INSERT INTO public.piloto VALUES (553, NULL, NULL, 'André', 'Testut', 'Monegasque');
INSERT INTO public.piloto VALUES (554, NULL, NULL, 'Jean', 'Behra', 'French');
INSERT INTO public.piloto VALUES (555, NULL, NULL, 'Paul', 'Russo', 'American');
INSERT INTO public.piloto VALUES (556, NULL, NULL, 'Jimmy', 'Daywalt', 'American');
INSERT INTO public.piloto VALUES (557, NULL, NULL, 'Chuck', 'Arnold', 'American');
INSERT INTO public.piloto VALUES (558, NULL, NULL, 'Al', 'Keller', 'American');
INSERT INTO public.piloto VALUES (559, NULL, NULL, 'Pat', 'Flaherty', 'American');
INSERT INTO public.piloto VALUES (560, NULL, NULL, 'Bill', 'Cheesbourg', 'American');
INSERT INTO public.piloto VALUES (561, NULL, NULL, 'Ray', 'Crawford', 'American');
INSERT INTO public.piloto VALUES (562, NULL, NULL, 'Jack', 'Turner', 'American');
INSERT INTO public.piloto VALUES (563, NULL, NULL, 'Chuck', 'Weyant', 'American');
INSERT INTO public.piloto VALUES (564, NULL, NULL, 'Jud', 'Larson', 'American');
INSERT INTO public.piloto VALUES (565, NULL, NULL, 'Mike', 'Magill', 'American');
INSERT INTO public.piloto VALUES (566, NULL, NULL, 'Carroll', 'Shelby', 'American');
INSERT INTO public.piloto VALUES (567, NULL, NULL, 'Fritz', 'd''Orey', 'Brazilian');
INSERT INTO public.piloto VALUES (568, NULL, NULL, 'Azdrubal', 'Fontes', 'Uruguayan');
INSERT INTO public.piloto VALUES (569, NULL, NULL, 'Peter', 'Ashdown', 'British');
INSERT INTO public.piloto VALUES (570, NULL, NULL, 'Bill', 'Moss', 'British');
INSERT INTO public.piloto VALUES (571, NULL, NULL, 'Dennis', 'Taylor', 'British');
INSERT INTO public.piloto VALUES (572, NULL, NULL, 'Harry', 'Blanchard', 'American');
INSERT INTO public.piloto VALUES (573, NULL, NULL, 'Alessandro', 'de Tomaso', 'Argentine-Italian');
INSERT INTO public.piloto VALUES (574, NULL, NULL, 'George', 'Constantine', 'American');
INSERT INTO public.piloto VALUES (575, NULL, NULL, 'Bob', 'Said', 'American');
INSERT INTO public.piloto VALUES (576, NULL, NULL, 'Phil', 'Cade', 'American');
INSERT INTO public.piloto VALUES (577, NULL, NULL, 'Luigi', 'Musso', 'Italian');
INSERT INTO public.piloto VALUES (578, NULL, NULL, 'Mike', 'Hawthorn', 'British');
INSERT INTO public.piloto VALUES (579, NULL, NULL, 'Juan', 'Fangio', 'Argentine');
INSERT INTO public.piloto VALUES (580, NULL, NULL, 'Paco', 'Godia', 'Spanish');
INSERT INTO public.piloto VALUES (581, NULL, NULL, 'Peter', 'Collins', 'British');
INSERT INTO public.piloto VALUES (582, NULL, NULL, 'Ken', 'Kavanagh', 'Australian');
INSERT INTO public.piloto VALUES (583, NULL, NULL, 'Gerino', 'Gerini', 'Italian');
INSERT INTO public.piloto VALUES (584, NULL, NULL, 'Bruce', 'Kessler', 'American');
INSERT INTO public.piloto VALUES (585, NULL, NULL, 'Paul', 'Emery', 'British');
INSERT INTO public.piloto VALUES (586, NULL, NULL, 'Luigi', 'Piotti', 'Italian');
INSERT INTO public.piloto VALUES (587, NULL, NULL, 'Bernie', 'Ecclestone', 'British');
INSERT INTO public.piloto VALUES (588, NULL, NULL, 'Luigi', 'Taramazzo', 'Italian');
INSERT INTO public.piloto VALUES (589, NULL, NULL, 'Louis', 'Chiron', 'Monegasque');
INSERT INTO public.piloto VALUES (590, NULL, NULL, 'Stuart', 'Lewis-Evans', 'British');
INSERT INTO public.piloto VALUES (591, NULL, NULL, 'George', 'Amick', 'American');
INSERT INTO public.piloto VALUES (592, NULL, NULL, 'Jimmy', 'Reece', 'American');
INSERT INTO public.piloto VALUES (593, NULL, NULL, 'Johnnie', 'Parsons', 'American');
INSERT INTO public.piloto VALUES (594, NULL, NULL, 'Johnnie', 'Tolan', 'American');
INSERT INTO public.piloto VALUES (595, NULL, NULL, 'Billy', 'Garrett', 'American');
INSERT INTO public.piloto VALUES (596, NULL, NULL, 'Ed', 'Elisian', 'American');
INSERT INTO public.piloto VALUES (597, NULL, NULL, 'Pat', 'O''Connor', 'American');
INSERT INTO public.piloto VALUES (598, NULL, NULL, 'Jerry', 'Unser', 'American');
INSERT INTO public.piloto VALUES (599, NULL, NULL, 'Art', 'Bisch', 'American');
INSERT INTO public.piloto VALUES (600, NULL, NULL, 'Christian', 'Goethals', 'Belgian');
INSERT INTO public.piloto VALUES (601, NULL, NULL, 'Dick', 'Gibson', 'British');
INSERT INTO public.piloto VALUES (602, NULL, NULL, 'Robert', 'La Caze', 'French');
INSERT INTO public.piloto VALUES (603, NULL, NULL, 'André', 'Guelfi', 'French');
INSERT INTO public.piloto VALUES (604, NULL, NULL, 'François', 'Picard', 'French');
INSERT INTO public.piloto VALUES (605, NULL, NULL, 'Tom', 'Bridger', 'British');
INSERT INTO public.piloto VALUES (606, NULL, NULL, 'Alfonso', 'de Portago', 'Spanish');
INSERT INTO public.piloto VALUES (607, NULL, NULL, 'Cesare', 'Perdisa', 'Italian');
INSERT INTO public.piloto VALUES (608, NULL, NULL, 'Eugenio', 'Castellotti', 'Italian');
INSERT INTO public.piloto VALUES (609, NULL, NULL, 'André', 'Simon', 'French');
INSERT INTO public.piloto VALUES (610, NULL, NULL, 'Les', 'Leston', 'British');
INSERT INTO public.piloto VALUES (611, NULL, NULL, 'Sam', 'Hanks', 'American');
INSERT INTO public.piloto VALUES (612, NULL, NULL, 'Andy', 'Linden', 'American');
INSERT INTO public.piloto VALUES (613, NULL, NULL, 'Marshall', 'Teague', 'American');
INSERT INTO public.piloto VALUES (614, NULL, NULL, 'Don', 'Edmunds', 'American');
INSERT INTO public.piloto VALUES (615, NULL, NULL, 'Fred', 'Agabashian', 'American');
INSERT INTO public.piloto VALUES (616, NULL, NULL, 'Elmer', 'George', 'American');
INSERT INTO public.piloto VALUES (617, NULL, NULL, 'Mike', 'MacDowel', 'British');
INSERT INTO public.piloto VALUES (618, NULL, NULL, 'Herbert', 'MacKay-Fraser', 'American');
INSERT INTO public.piloto VALUES (619, NULL, NULL, 'Bob', 'Gerard', 'British');
INSERT INTO public.piloto VALUES (620, NULL, NULL, 'Umberto', 'Maglioli', 'Italian');
INSERT INTO public.piloto VALUES (621, NULL, NULL, 'Paul', 'England', 'Australian');
INSERT INTO public.piloto VALUES (622, NULL, NULL, 'Chico', 'Landi', 'Brazilian');
INSERT INTO public.piloto VALUES (623, NULL, NULL, 'Alberto', 'Uria', 'Uruguayan');
INSERT INTO public.piloto VALUES (624, NULL, NULL, 'Hernando', 'da Silva Ramos', 'Brazilian');
INSERT INTO public.piloto VALUES (625, NULL, NULL, 'Élie', 'Bayol', 'French');
INSERT INTO public.piloto VALUES (626, NULL, NULL, 'Robert', 'Manzon', 'French');
INSERT INTO public.piloto VALUES (627, NULL, NULL, 'Louis', 'Rosier', 'French');
INSERT INTO public.piloto VALUES (628, NULL, NULL, 'Bob', 'Sweikert', 'American');
INSERT INTO public.piloto VALUES (629, NULL, NULL, 'Cliff', 'Griffith', 'American');
INSERT INTO public.piloto VALUES (630, NULL, NULL, 'Duke', 'Dinsmore', 'American');
INSERT INTO public.piloto VALUES (631, NULL, NULL, 'Keith', 'Andrews', 'American');
INSERT INTO public.piloto VALUES (632, NULL, NULL, 'Paul', 'Frère', 'Belgian');
INSERT INTO public.piloto VALUES (633, NULL, NULL, 'Luigi', 'Villoresi', 'Italian');
INSERT INTO public.piloto VALUES (634, NULL, NULL, 'Piero', 'Scotti', 'Italian');
INSERT INTO public.piloto VALUES (635, NULL, NULL, 'Colin', 'Chapman', 'British');
INSERT INTO public.piloto VALUES (636, NULL, NULL, 'Desmond', 'Titterington', 'British');
INSERT INTO public.piloto VALUES (637, NULL, NULL, 'Archie', 'Scott Brown', 'British');
INSERT INTO public.piloto VALUES (638, NULL, NULL, 'Ottorino', 'Volonterio', 'Swiss');
INSERT INTO public.piloto VALUES (639, NULL, NULL, 'André', 'Milhoux', 'Belgian');
INSERT INTO public.piloto VALUES (640, NULL, NULL, 'Toulo', 'de Graffenried', 'Swiss');
INSERT INTO public.piloto VALUES (641, NULL, NULL, 'Piero', 'Taruffi', 'Italian');
INSERT INTO public.piloto VALUES (642, NULL, NULL, 'Nino', 'Farina', 'Italian');
INSERT INTO public.piloto VALUES (643, NULL, NULL, 'Roberto', 'Mieres', 'Argentine');
INSERT INTO public.piloto VALUES (644, NULL, NULL, 'Sergio', 'Mantovani', 'Italian');
INSERT INTO public.piloto VALUES (645, NULL, NULL, 'Clemar', 'Bucci', 'Argentine');
INSERT INTO public.piloto VALUES (646, NULL, NULL, 'Jesús', 'Iglesias', 'Argentine');
INSERT INTO public.piloto VALUES (647, NULL, NULL, 'Alberto', 'Ascari', 'Italian');
INSERT INTO public.piloto VALUES (648, NULL, NULL, 'Karl', 'Kling', 'German');
INSERT INTO public.piloto VALUES (649, NULL, NULL, 'Pablo', 'Birger', 'Argentine');
INSERT INTO public.piloto VALUES (650, NULL, NULL, 'Jacques', 'Pollet', 'French');
INSERT INTO public.piloto VALUES (651, NULL, NULL, 'Lance', 'Macklin', 'British');
INSERT INTO public.piloto VALUES (652, NULL, NULL, 'Ted', 'Whiteaway', 'British');
INSERT INTO public.piloto VALUES (653, NULL, NULL, 'Jimmy', 'Davies', 'American');
INSERT INTO public.piloto VALUES (654, NULL, NULL, 'Walt', 'Faulkner', 'American');
INSERT INTO public.piloto VALUES (655, NULL, NULL, 'Cal', 'Niday', 'American');
INSERT INTO public.piloto VALUES (656, NULL, NULL, 'Art', 'Cross', 'American');
INSERT INTO public.piloto VALUES (657, NULL, NULL, 'Bill', 'Vukovich', 'American');
INSERT INTO public.piloto VALUES (658, NULL, NULL, 'Jack', 'McGrath', 'American');
INSERT INTO public.piloto VALUES (659, NULL, NULL, 'Jerry', 'Hoyt', 'American');
INSERT INTO public.piloto VALUES (660, NULL, NULL, 'Johnny', 'Claes', 'Belgian');
INSERT INTO public.piloto VALUES (661, NULL, NULL, 'Peter', 'Walker', 'British');
INSERT INTO public.piloto VALUES (662, NULL, NULL, 'Mike', 'Sparken', 'French');
INSERT INTO public.piloto VALUES (663, NULL, NULL, 'Ken', 'Wharton', 'British');
INSERT INTO public.piloto VALUES (664, NULL, NULL, 'Kenneth', 'McAlpine', 'British');
INSERT INTO public.piloto VALUES (665, NULL, NULL, 'Leslie', 'Marr', 'British');
INSERT INTO public.piloto VALUES (666, NULL, NULL, 'Tony', 'Rolt', 'British');
INSERT INTO public.piloto VALUES (667, NULL, NULL, 'John', 'Fitch', 'American');
INSERT INTO public.piloto VALUES (668, NULL, NULL, 'Jean', 'Lucas', 'French');
INSERT INTO public.piloto VALUES (669, NULL, NULL, 'Prince', 'Bira', 'Thai');
INSERT INTO public.piloto VALUES (670, NULL, NULL, 'Onofre', 'Marimón', 'Argentine');
INSERT INTO public.piloto VALUES (671, NULL, NULL, 'Roger', 'Loyer', 'French');
INSERT INTO public.piloto VALUES (672, NULL, NULL, 'Jorge', 'Daponte', 'Argentine');
INSERT INTO public.piloto VALUES (673, NULL, NULL, 'Mike', 'Nazaruk', 'American');
INSERT INTO public.piloto VALUES (674, NULL, NULL, 'Larry', 'Crockett', 'American');
INSERT INTO public.piloto VALUES (675, NULL, NULL, 'Manny', 'Ayulo', 'American');
INSERT INTO public.piloto VALUES (676, NULL, NULL, 'Frank', 'Armi', 'American');
INSERT INTO public.piloto VALUES (677, NULL, NULL, 'Travis', 'Webb', 'American');
INSERT INTO public.piloto VALUES (678, NULL, NULL, 'Len', 'Duncan', 'American');
INSERT INTO public.piloto VALUES (679, NULL, NULL, 'Ernie', 'McCoy', 'American');
INSERT INTO public.piloto VALUES (680, NULL, NULL, 'Jacques', 'Swaters', 'American');
INSERT INTO public.piloto VALUES (681, NULL, NULL, 'Georges', 'Berger', 'Belgian');
INSERT INTO public.piloto VALUES (682, NULL, NULL, 'Don', 'Beauman', 'British');
INSERT INTO public.piloto VALUES (683, NULL, NULL, 'Leslie', 'Thorne', 'British');
INSERT INTO public.piloto VALUES (684, NULL, NULL, 'Bill', 'Whitehouse', 'British');
INSERT INTO public.piloto VALUES (685, NULL, NULL, 'John', 'Riseley-Prichard', 'British');
INSERT INTO public.piloto VALUES (686, NULL, NULL, 'Reg', 'Parnell', 'British');
INSERT INTO public.piloto VALUES (687, NULL, NULL, 'Peter', 'Whitehead', 'British');
INSERT INTO public.piloto VALUES (688, NULL, NULL, 'Eric', 'Brandon', 'British');
INSERT INTO public.piloto VALUES (689, NULL, NULL, 'Alan', 'Brown', 'British');
INSERT INTO public.piloto VALUES (690, NULL, NULL, 'Rodney', 'Nuckey', 'British');
INSERT INTO public.piloto VALUES (691, NULL, NULL, 'Hermann', 'Lang', 'German');
INSERT INTO public.piloto VALUES (692, NULL, NULL, 'Theo', 'Helfrich', 'German');
INSERT INTO public.piloto VALUES (693, NULL, NULL, 'Fred', 'Wacker', 'American');
INSERT INTO public.piloto VALUES (694, NULL, NULL, 'Giovanni', 'de Riu', 'Italian');
INSERT INTO public.piloto VALUES (695, NULL, NULL, 'Oscar', 'Gálvez', 'Argentine');
INSERT INTO public.piloto VALUES (696, NULL, NULL, 'John', 'Barber', 'British');
INSERT INTO public.piloto VALUES (697, NULL, NULL, 'Felice', 'Bonetto', 'Italian');
INSERT INTO public.piloto VALUES (698, NULL, NULL, 'Adolfo', 'Cruz', 'Argentine');
INSERT INTO public.piloto VALUES (699, NULL, NULL, 'Duke', 'Nalon', 'American');
INSERT INTO public.piloto VALUES (700, NULL, NULL, 'Carl', 'Scarborough', 'American');
INSERT INTO public.piloto VALUES (701, NULL, NULL, 'Bill', 'Holland', 'American');
INSERT INTO public.piloto VALUES (702, NULL, NULL, 'Bob', 'Scott', 'American');
INSERT INTO public.piloto VALUES (703, NULL, NULL, 'Arthur', 'Legat', 'Belgian');
INSERT INTO public.piloto VALUES (704, NULL, NULL, 'Yves', 'Cabantous', 'French');
INSERT INTO public.piloto VALUES (705, NULL, NULL, 'Tony', 'Crook', 'British');
INSERT INTO public.piloto VALUES (706, NULL, NULL, 'Jimmy', 'Stewart', 'British');
INSERT INTO public.piloto VALUES (707, NULL, NULL, 'Ian', 'Stewart', 'British');
INSERT INTO public.piloto VALUES (708, NULL, NULL, 'Duncan', 'Hamilton', 'British');
INSERT INTO public.piloto VALUES (709, NULL, NULL, 'Ernst', 'Klodwig', 'East German');
INSERT INTO public.piloto VALUES (710, NULL, NULL, 'Rudolf', 'Krause', 'East German');
INSERT INTO public.piloto VALUES (711, NULL, NULL, 'Oswald', 'Karch', 'German');
INSERT INTO public.piloto VALUES (712, NULL, NULL, 'Willi', 'Heeks', 'German');
INSERT INTO public.piloto VALUES (713, NULL, NULL, 'Theo', 'Fitzau', 'East German');
INSERT INTO public.piloto VALUES (714, NULL, NULL, 'Kurt', 'Adolff', 'German');
INSERT INTO public.piloto VALUES (715, NULL, NULL, 'Günther', 'Bechem', 'German');
INSERT INTO public.piloto VALUES (716, NULL, NULL, 'Erwin', 'Bauer', 'German');
INSERT INTO public.piloto VALUES (717, NULL, NULL, 'Hans', 'von Stuck', 'German');
INSERT INTO public.piloto VALUES (718, NULL, NULL, 'Ernst', 'Loof', 'German');
INSERT INTO public.piloto VALUES (719, NULL, NULL, 'Albert', 'Scherrer', 'Swiss');
INSERT INTO public.piloto VALUES (720, NULL, NULL, 'Max', 'de Terra', 'Swiss');
INSERT INTO public.piloto VALUES (721, NULL, NULL, 'Peter', 'Hirt', 'Swiss');
INSERT INTO public.piloto VALUES (722, NULL, NULL, 'Piero', 'Carini', 'Italian');
INSERT INTO public.piloto VALUES (723, NULL, NULL, 'Rudi', 'Fischer', 'Swiss');
INSERT INTO public.piloto VALUES (724, NULL, NULL, 'Toni', 'Ulmen', 'German');
INSERT INTO public.piloto VALUES (725, NULL, NULL, 'George', 'Abecassis', 'British');
INSERT INTO public.piloto VALUES (726, NULL, NULL, 'George', 'Connor', 'American');
INSERT INTO public.piloto VALUES (727, NULL, NULL, 'Jim', 'Rigsby', 'American');
INSERT INTO public.piloto VALUES (728, NULL, NULL, 'Joe', 'James', 'American');
INSERT INTO public.piloto VALUES (729, NULL, NULL, 'Bill', 'Schindler', 'American');
INSERT INTO public.piloto VALUES (730, NULL, NULL, 'George', 'Fonder', 'American');
INSERT INTO public.piloto VALUES (731, NULL, NULL, 'Henry', 'Banks', 'American');
INSERT INTO public.piloto VALUES (732, NULL, NULL, 'Johnny', 'McDowell', 'American');
INSERT INTO public.piloto VALUES (733, NULL, NULL, 'Chet', 'Miller', 'American');
INSERT INTO public.piloto VALUES (734, NULL, NULL, 'Bobby', 'Ball', 'American');
INSERT INTO public.piloto VALUES (735, NULL, NULL, 'Charles', 'de Tornaco', 'Belgian');
INSERT INTO public.piloto VALUES (736, NULL, NULL, 'Roger', 'Laurent', 'Belgian');
INSERT INTO public.piloto VALUES (737, NULL, NULL, 'Robert', 'O''Brien', 'American');
INSERT INTO public.piloto VALUES (738, NULL, NULL, 'Tony', 'Gaze', 'Australian');
INSERT INTO public.piloto VALUES (739, NULL, NULL, 'Robin', 'Montgomerie-Charrington', 'British');
INSERT INTO public.piloto VALUES (740, NULL, NULL, 'Franco', 'Comotti', 'Italian');
INSERT INTO public.piloto VALUES (741, NULL, NULL, 'Philippe', 'Étancelin', 'French');
INSERT INTO public.piloto VALUES (742, NULL, NULL, 'Dennis', 'Poore', 'British');
INSERT INTO public.piloto VALUES (743, NULL, NULL, 'Eric', 'Thompson', 'British');
INSERT INTO public.piloto VALUES (744, NULL, NULL, 'Ken', 'Downing', 'British');
INSERT INTO public.piloto VALUES (745, NULL, NULL, 'Graham', 'Whitehead', 'British');
INSERT INTO public.piloto VALUES (746, NULL, NULL, 'Gino', 'Bianco', 'Brazilian');
INSERT INTO public.piloto VALUES (747, NULL, NULL, 'David', 'Murray', 'British');
INSERT INTO public.piloto VALUES (748, NULL, NULL, 'Eitel', 'Cantoni', 'Uruguayan');
INSERT INTO public.piloto VALUES (749, NULL, NULL, 'Bill', 'Aston', 'British');
INSERT INTO public.piloto VALUES (750, NULL, NULL, 'Adolf', 'Brudes', 'German');
INSERT INTO public.piloto VALUES (751, NULL, NULL, 'Fritz', 'Riess', 'German');
INSERT INTO public.piloto VALUES (752, NULL, NULL, 'Helmut', 'Niedermayr', 'German');
INSERT INTO public.piloto VALUES (753, NULL, NULL, 'Hans', 'Klenk', 'German');
INSERT INTO public.piloto VALUES (754, NULL, NULL, 'Marcel', 'Balsa', 'French');
INSERT INTO public.piloto VALUES (755, NULL, NULL, 'Rudolf', 'Schoeller', 'Swiss');
INSERT INTO public.piloto VALUES (756, NULL, NULL, 'Paul', 'Pietsch', 'German');
INSERT INTO public.piloto VALUES (757, NULL, NULL, 'Josef', 'Peters', 'German');
INSERT INTO public.piloto VALUES (758, NULL, NULL, 'Dries', 'van der Lof', 'Dutch');
INSERT INTO public.piloto VALUES (759, NULL, NULL, 'Jan', 'Flinterman', 'Dutch');
INSERT INTO public.piloto VALUES (760, NULL, NULL, 'Piero', 'Dusio', 'Italian');
INSERT INTO public.piloto VALUES (761, NULL, NULL, 'Alberto', 'Crespo', 'Argentine');
INSERT INTO public.piloto VALUES (762, NULL, NULL, 'Franco', 'Rol', 'Italian');
INSERT INTO public.piloto VALUES (763, NULL, NULL, 'Consalvo', 'Sanesi', 'Italian');
INSERT INTO public.piloto VALUES (764, NULL, NULL, 'Guy', 'Mairesse', 'French');
INSERT INTO public.piloto VALUES (765, NULL, NULL, 'Henri', 'Louveau', 'French');
INSERT INTO public.piloto VALUES (766, NULL, NULL, 'Lee', 'Wallard', 'American');
INSERT INTO public.piloto VALUES (767, NULL, NULL, 'Carl', 'Forberg', 'American');
INSERT INTO public.piloto VALUES (768, NULL, NULL, 'Mauri', 'Rose', 'American');
INSERT INTO public.piloto VALUES (769, NULL, NULL, 'Bill', 'Mackey', 'American');
INSERT INTO public.piloto VALUES (770, NULL, NULL, 'Cecil', 'Green', 'American');
INSERT INTO public.piloto VALUES (771, NULL, NULL, 'Walt', 'Brown', 'American');
INSERT INTO public.piloto VALUES (772, NULL, NULL, 'Mack', 'Hellings', 'American');
INSERT INTO public.piloto VALUES (773, NULL, NULL, 'Pierre', 'Levegh', 'French');
INSERT INTO public.piloto VALUES (774, NULL, NULL, 'Eugène', 'Chaboud', 'French');
INSERT INTO public.piloto VALUES (775, NULL, NULL, 'Aldo', 'Gordini', 'French');
INSERT INTO public.piloto VALUES (776, NULL, NULL, 'Joe', 'Kelly', 'Irish');
INSERT INTO public.piloto VALUES (777, NULL, NULL, 'Philip', 'Fotheringham-Parker', 'British');
INSERT INTO public.piloto VALUES (778, NULL, NULL, 'Brian', 'Shawe Taylor', 'British');
INSERT INTO public.piloto VALUES (779, NULL, NULL, 'John', 'James', 'British');
INSERT INTO public.piloto VALUES (780, NULL, NULL, 'Toni', 'Branca', 'Swiss');
INSERT INTO public.piloto VALUES (781, NULL, NULL, 'Ken', 'Richardson', 'British');
INSERT INTO public.piloto VALUES (782, NULL, NULL, 'Juan', 'Jover', 'Spanish');
INSERT INTO public.piloto VALUES (783, NULL, NULL, 'Georges', 'Grignard', 'French');
INSERT INTO public.piloto VALUES (784, NULL, NULL, 'David', 'Hampshire', 'British');
INSERT INTO public.piloto VALUES (785, NULL, NULL, 'Geoff', 'Crossley', 'British');
INSERT INTO public.piloto VALUES (786, NULL, NULL, 'Luigi', 'Fagioli', 'Italian');
INSERT INTO public.piloto VALUES (787, NULL, NULL, 'Cuth', 'Harrison', 'British');
INSERT INTO public.piloto VALUES (788, NULL, NULL, 'Joe', 'Fry', 'British');
INSERT INTO public.piloto VALUES (789, NULL, NULL, 'Eugène', 'Martin', 'French');
INSERT INTO public.piloto VALUES (790, NULL, NULL, 'Leslie', 'Johnson', 'British');
INSERT INTO public.piloto VALUES (791, NULL, NULL, 'Clemente', 'Biondetti', 'Italian');
INSERT INTO public.piloto VALUES (792, NULL, NULL, 'Alfredo', 'Pián', 'Argentine');
INSERT INTO public.piloto VALUES (793, NULL, NULL, 'Raymond', 'Sommer', 'French');
INSERT INTO public.piloto VALUES (794, NULL, NULL, 'Joie', 'Chitwood', 'American');
INSERT INTO public.piloto VALUES (795, NULL, NULL, 'Myron', 'Fohr', 'American');
INSERT INTO public.piloto VALUES (796, NULL, NULL, 'Walt', 'Ader', 'American');
INSERT INTO public.piloto VALUES (797, NULL, NULL, 'Jackie', 'Holmes', 'American');
INSERT INTO public.piloto VALUES (798, NULL, NULL, 'Bayliss', 'Levrett', 'American');
INSERT INTO public.piloto VALUES (799, NULL, NULL, 'Jimmy', 'Jackson', 'American');
INSERT INTO public.piloto VALUES (800, NULL, NULL, 'Nello', 'Pagani', 'Italian');
INSERT INTO public.piloto VALUES (801, NULL, NULL, 'Charles', 'Pozzi', 'French');
INSERT INTO public.piloto VALUES (802, NULL, NULL, 'Dorino', 'Serafini', 'Italian');
INSERT INTO public.piloto VALUES (803, NULL, NULL, 'Bill', 'Cantrell', 'American');
INSERT INTO public.piloto VALUES (804, NULL, NULL, 'Johnny', 'Mantz', 'American');
INSERT INTO public.piloto VALUES (805, NULL, NULL, 'Danny', 'Kladis', 'American');
INSERT INTO public.piloto VALUES (806, NULL, NULL, 'Óscar', 'González', 'Uruguayan');
INSERT INTO public.piloto VALUES (807, 27, 'HUL', 'Nico', 'Hülkenberg', 'German');
INSERT INTO public.piloto VALUES (808, NULL, 'PET', 'Vitaly', 'Petrov', 'Russian');
INSERT INTO public.piloto VALUES (810, NULL, 'DIG', 'Lucas', 'di Grassi', 'Brazilian');
INSERT INTO public.piloto VALUES (811, NULL, 'SEN', 'Bruno', 'Senna', 'Brazilian');
INSERT INTO public.piloto VALUES (812, NULL, 'CHA', 'Karun', 'Chandhok', 'Indian');
INSERT INTO public.piloto VALUES (813, 13, 'MAL', 'Pastor', 'Maldonado', 'Venezuelan');
INSERT INTO public.piloto VALUES (814, 40, 'DIR', 'Paul', 'di Resta', 'British');
INSERT INTO public.piloto VALUES (815, 11, 'PER', 'Sergio', 'Pérez', 'Mexican');
INSERT INTO public.piloto VALUES (816, NULL, 'DAM', 'Jérôme', 'd''Ambrosio', 'Belgian');
INSERT INTO public.piloto VALUES (817, 3, 'RIC', 'Daniel', 'Ricciardo', 'Australian');
INSERT INTO public.piloto VALUES (818, 25, 'VER', 'Jean-Éric', 'Vergne', 'French');
INSERT INTO public.piloto VALUES (819, NULL, 'PIC', 'Charles', 'Pic', 'French');
INSERT INTO public.piloto VALUES (820, 4, 'CHI', 'Max', 'Chilton', 'British');
INSERT INTO public.piloto VALUES (821, 21, 'GUT', 'Esteban', 'Gutiérrez', 'Mexican');
INSERT INTO public.piloto VALUES (822, 77, 'BOT', 'Valtteri', 'Bottas', 'Finnish');
INSERT INTO public.piloto VALUES (823, NULL, 'VDG', 'Giedo', 'van der Garde', 'Dutch');
INSERT INTO public.piloto VALUES (824, 17, 'BIA', 'Jules', 'Bianchi', 'French');
INSERT INTO public.piloto VALUES (825, 20, 'MAG', 'Kevin', 'Magnussen', 'Danish');
INSERT INTO public.piloto VALUES (826, 26, 'KVY', 'Daniil', 'Kvyat', 'Russian');
INSERT INTO public.piloto VALUES (827, 45, 'LOT', 'André', 'Lotterer', 'German');
INSERT INTO public.piloto VALUES (828, 9, 'ERI', 'Marcus', 'Ericsson', 'Swedish');
INSERT INTO public.piloto VALUES (829, 28, 'STE', 'Will', 'Stevens', 'British');
INSERT INTO public.piloto VALUES (830, 33, 'VER', 'Max', 'Verstappen', 'Dutch');
INSERT INTO public.piloto VALUES (831, 12, 'NAS', 'Felipe', 'Nasr', 'Brazilian');
INSERT INTO public.piloto VALUES (832, 55, 'SAI', 'Carlos', 'Sainz', 'Spanish');
INSERT INTO public.piloto VALUES (833, 98, 'MER', 'Roberto', 'Merhi', 'Spanish');
INSERT INTO public.piloto VALUES (834, 53, 'RSS', 'Alexander', 'Rossi', 'American');
INSERT INTO public.piloto VALUES (835, 30, 'PAL', 'Jolyon', 'Palmer', 'British');
INSERT INTO public.piloto VALUES (836, 94, 'WEH', 'Pascal', 'Wehrlein', 'German');
INSERT INTO public.piloto VALUES (837, 88, 'HAR', 'Rio', 'Haryanto', 'Indonesian');
INSERT INTO public.piloto VALUES (838, 2, 'VAN', 'Stoffel', 'Vandoorne', 'Belgian');
INSERT INTO public.piloto VALUES (839, 31, 'OCO', 'Esteban', 'Ocon', 'French');
INSERT INTO public.piloto VALUES (840, 18, 'STR', 'Lance', 'Stroll', 'Canadian');
INSERT INTO public.piloto VALUES (841, 99, 'GIO', 'Antonio', 'Giovinazzi', 'Italian');
INSERT INTO public.piloto VALUES (843, 28, 'HAR', 'Brendon', 'Hartley', 'New Zealander');
INSERT INTO public.piloto VALUES (844, 16, 'LEC', 'Charles', 'Leclerc', 'Monegasque');
INSERT INTO public.piloto VALUES (845, 35, 'SIR', 'Sergey', 'Sirotkin', 'Russian');
INSERT INTO public.piloto VALUES (846, 4, 'NOR', 'Lando', 'Norris', 'British');
INSERT INTO public.piloto VALUES (847, 63, 'RUS', 'George', 'Russell', 'British');
INSERT INTO public.piloto VALUES (848, 23, 'ALB', 'Alexander', 'Albon', 'Thai');
INSERT INTO public.piloto VALUES (849, 6, 'LAT', 'Nicholas', 'Latifi', 'Canadian');
INSERT INTO public.piloto VALUES (850, 51, 'FIT', 'Pietro', 'Fittipaldi', 'Brazilian');
INSERT INTO public.piloto VALUES (851, 89, 'AIT', 'Jack', 'Aitken', 'British');
INSERT INTO public.piloto VALUES (852, 22, 'TSU', 'Yuki', 'Tsunoda', 'Japanese');
INSERT INTO public.piloto VALUES (853, 9, 'MAZ', 'Nikita', 'Mazepin', 'Russian');
INSERT INTO public.piloto VALUES (854, 47, 'MSC', 'Mick', 'Schumacher', 'German');
INSERT INTO public.piloto VALUES (855, 24, 'ZHO', 'Guanyu', 'Zhou', 'Chinese');
INSERT INTO public.piloto VALUES (856, 21, 'DEV', 'Nyck', 'de Vries', 'Dutch');
INSERT INTO public.piloto VALUES (857, 81, 'PIA', 'Oscar', 'Piastri', 'Australian');
INSERT INTO public.piloto VALUES (858, 2, 'SAR', 'Logan', 'Sargeant', 'American');
INSERT INTO public.piloto VALUES (859, 15, 'LAW', 'Liam', 'Lawson', 'New Zealander');


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public.post VALUES (41, 'Esse posto e sobre os melhores corredores', '2023-10-26 12:36:02.606025-03', 'Pedro', 2);
INSERT INTO public.post VALUES (43, 'asada', '2023-10-26 00:00:00-03', 'Pedro', 2);
INSERT INTO public.post VALUES (44, 'asxdasdas', '2023-10-26 00:00:00-03', 'Pedro', 3);
INSERT INTO public.post VALUES (48, 'esse e o melhor circuito ', '2023-10-26 00:00:00-03', 'Pedro', 3);
INSERT INTO public.post VALUES (50, 'hamilton o melhor', '2023-10-26 00:00:00-03', 'Pedro', 3);
INSERT INTO public.post VALUES (53, 'ESSE CARA E LEGAL', '2023-10-29 00:00:00-03', 'yas', 3);
INSERT INTO public.post VALUES (54, 'zcsadsa', '2023-11-23 00:00:00-03', 'd', 2);
INSERT INTO public.post VALUES (55, 'top', '2023-11-23 00:00:00-03', 'p', 2);
INSERT INTO public.post VALUES (56, 'cu ', '2023-11-23 00:00:00-03', 'p', 2);
INSERT INTO public.post VALUES (57, 'oi', '2023-11-23 00:00:00-03', 'p', 2);
INSERT INTO public.post VALUES (58, 'asd', '2023-11-23 00:00:00-03', 'p', 3);


--
-- Data for Name: resposta; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public.resposta VALUES (43, 'eu concordo com o que voce disse', '2023-10-26 21:07:36.745223-03', 41, NULL, 'pedrinho', 3);
INSERT INTO public.resposta VALUES (44, 'e se voce pensar por esse lado', '2023-10-26 21:08:02.367693-03', 43, 43, 'Pedro', 2);
INSERT INTO public.resposta VALUES (45, 'a', '2023-10-29 00:00:00-03', 43, NULL, NULL, 2);
INSERT INTO public.resposta VALUES (46, 'eu nao ', '2023-10-29 00:00:00-03', 41, 43, NULL, 3);
INSERT INTO public.resposta VALUES (47, 'joia ', '2023-10-29 00:00:00-03', 41, 43, 'yas', 3);
INSERT INTO public.resposta VALUES (48, 'asdsadhsadhj', '2023-10-30 00:00:00-03', 43, 45, NULL, 2);
INSERT INTO public.resposta VALUES (49, 'sakjdasljkdjjsa', '2023-10-30 00:00:00-03', 41, 43, 'Pedro', 3);
INSERT INTO public.resposta VALUES (50, 'okok', '2023-11-17 00:00:00-03', 41, 43, NULL, 3);
INSERT INTO public.resposta VALUES (51, 'asdfad', '2023-11-17 00:00:00-03', 41, 43, 'a', 3);
INSERT INTO public.resposta VALUES (52, 'eu gostei', '2023-11-17 00:00:00-03', 41, 43, 'a', 3);
INSERT INTO public.resposta VALUES (53, 'lewis hamilton goat', '2023-11-17 00:00:00-03', 41, NULL, 'a', 2);
INSERT INTO public.resposta VALUES (54, 'eu gostei', '2023-11-17 00:00:00-03', 41, NULL, NULL, 2);
INSERT INTO public.resposta VALUES (55, 'eu gostei', '2023-11-17 00:00:00-03', 41, NULL, NULL, 2);
INSERT INTO public.resposta VALUES (56, 'eu gostei', '2023-11-17 00:00:00-03', 41, NULL, NULL, 2);
INSERT INTO public.resposta VALUES (57, 'sfds', '2023-11-17 00:00:00-03', 41, 43, NULL, 3);
INSERT INTO public.resposta VALUES (58, 'acabei de inserir', '2023-11-17 00:00:00-03', 41, 43, NULL, 3);
INSERT INTO public.resposta VALUES (59, 'adfsd', '2023-11-17 00:00:00-03', 41, 43, NULL, 3);
INSERT INTO public.resposta VALUES (60, 'dfaa', '2023-11-20 00:00:00-03', 41, 57, NULL, 3);
INSERT INTO public.resposta VALUES (61, 'fodasse', '2023-11-20 00:00:00-03', 41, 43, NULL, 3);


--
-- Data for Name: temporada; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public.temporada VALUES (2009);
INSERT INTO public.temporada VALUES (2008);
INSERT INTO public.temporada VALUES (2007);
INSERT INTO public.temporada VALUES (2006);
INSERT INTO public.temporada VALUES (2005);
INSERT INTO public.temporada VALUES (2004);
INSERT INTO public.temporada VALUES (2003);
INSERT INTO public.temporada VALUES (2002);
INSERT INTO public.temporada VALUES (2001);
INSERT INTO public.temporada VALUES (2000);
INSERT INTO public.temporada VALUES (1999);
INSERT INTO public.temporada VALUES (1998);
INSERT INTO public.temporada VALUES (1997);
INSERT INTO public.temporada VALUES (1996);
INSERT INTO public.temporada VALUES (1995);
INSERT INTO public.temporada VALUES (1994);
INSERT INTO public.temporada VALUES (1993);
INSERT INTO public.temporada VALUES (1992);
INSERT INTO public.temporada VALUES (1991);
INSERT INTO public.temporada VALUES (1990);
INSERT INTO public.temporada VALUES (2010);
INSERT INTO public.temporada VALUES (1989);
INSERT INTO public.temporada VALUES (1988);
INSERT INTO public.temporada VALUES (1987);
INSERT INTO public.temporada VALUES (1986);
INSERT INTO public.temporada VALUES (1985);
INSERT INTO public.temporada VALUES (1984);
INSERT INTO public.temporada VALUES (1983);
INSERT INTO public.temporada VALUES (1982);
INSERT INTO public.temporada VALUES (1981);
INSERT INTO public.temporada VALUES (1980);
INSERT INTO public.temporada VALUES (1979);
INSERT INTO public.temporada VALUES (1978);
INSERT INTO public.temporada VALUES (1977);
INSERT INTO public.temporada VALUES (1976);
INSERT INTO public.temporada VALUES (1975);
INSERT INTO public.temporada VALUES (1974);
INSERT INTO public.temporada VALUES (1973);
INSERT INTO public.temporada VALUES (1972);
INSERT INTO public.temporada VALUES (1971);
INSERT INTO public.temporada VALUES (1970);
INSERT INTO public.temporada VALUES (1969);
INSERT INTO public.temporada VALUES (1968);
INSERT INTO public.temporada VALUES (1967);
INSERT INTO public.temporada VALUES (1966);
INSERT INTO public.temporada VALUES (1965);
INSERT INTO public.temporada VALUES (1964);
INSERT INTO public.temporada VALUES (1963);
INSERT INTO public.temporada VALUES (1962);
INSERT INTO public.temporada VALUES (1961);
INSERT INTO public.temporada VALUES (1960);
INSERT INTO public.temporada VALUES (1959);
INSERT INTO public.temporada VALUES (1958);
INSERT INTO public.temporada VALUES (1957);
INSERT INTO public.temporada VALUES (1956);
INSERT INTO public.temporada VALUES (1955);
INSERT INTO public.temporada VALUES (1954);
INSERT INTO public.temporada VALUES (1953);
INSERT INTO public.temporada VALUES (1952);
INSERT INTO public.temporada VALUES (1951);
INSERT INTO public.temporada VALUES (1950);
INSERT INTO public.temporada VALUES (2011);
INSERT INTO public.temporada VALUES (2012);
INSERT INTO public.temporada VALUES (2013);
INSERT INTO public.temporada VALUES (2014);
INSERT INTO public.temporada VALUES (2015);
INSERT INTO public.temporada VALUES (2016);
INSERT INTO public.temporada VALUES (2017);
INSERT INTO public.temporada VALUES (2018);
INSERT INTO public.temporada VALUES (2019);
INSERT INTO public.temporada VALUES (2020);
INSERT INTO public.temporada VALUES (2021);
INSERT INTO public.temporada VALUES (2022);
INSERT INTO public.temporada VALUES (2023);


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; 
--

INSERT INTO public."user" VALUES (12, 'pedrinho', '', 'aaa', '2023-10-26 00:00:00-03', 1, 1, NULL);
INSERT INTO public."user" VALUES (15, 'asdasda', 'PedrinhoFormula1', 'aa', '2023-10-26 00:00:00-03', 1, 1, NULL);
INSERT INTO public."user" VALUES (16, 'yas', 'novoemailp@gmail.com', '12345', '2023-10-29 00:00:00-03', 1, 1, NULL);
INSERT INTO public."user" VALUES (14, 'Pedro', 'pedro@gmail.com', 'aa', '2023-10-26 00:00:00-03', 1, 1, NULL);
INSERT INTO public."user" VALUES (17, 'a', 'emailnovo@gmail.com', 'a', '2023-11-17 00:00:00-03', 1, 1, NULL);
INSERT INTO public."user" VALUES (18, 'b', 'b', 'b', '2023-11-21 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (19, 'c', 'c', 'c', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (20, 'd', 'd', 'd', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (21, 'e', 'e', 'e', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (22, 'f', 'f', 'f', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (23, 'g', 'g', 'g', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (24, 'h', 'h', 'h', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (25, 'p', 'p', 'p', '2023-11-23 00:00:00-03', 1, 1, -1);
INSERT INTO public."user" VALUES (26, 'contaHash', 'contaHASH@gmail.com', '202cb962ac59075b964b07152d234b70', '2023-11-23 00:00:00-03', 1, 1, 0);


--
-- Name: circuito_circuito_id_seq; Type: SEQUENCE SET; Schema: public; 
--

SELECT pg_catalog.setval('public.circuito_circuito_id_seq', 1, false);


--
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; 
--

SELECT pg_catalog.setval('public.post_post_id_seq', 58, true);


--
-- Name: resposta_reposta_pai_id_seq; Type: SEQUENCE SET; Schema: public; 
--

SELECT pg_catalog.setval('public.resposta_reposta_pai_id_seq', 1, false);


--
-- Name: resposta_resposta_id; Type: SEQUENCE SET; Schema: public; 
--

SELECT pg_catalog.setval('public.resposta_resposta_id', 61, true);


--
-- Name: user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.user_user_id_seq', 26, true);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (categoria_id);


--
-- Name: circuito circuito_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.circuito
    ADD CONSTRAINT circuito_pkey PRIMARY KEY (circuito_id);


--
-- Name: equipe equipe_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.equipe
    ADD CONSTRAINT equipe_pkey PRIMARY KEY (equipe_id);


--
-- Name: piloto piloto_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.piloto
    ADD CONSTRAINT piloto_pkey PRIMARY KEY (piloto_id);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (post_id);


--
-- Name: resposta resposta_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.resposta
    ADD CONSTRAINT resposta_pkey PRIMARY KEY (resposta_id);


--
-- Name: resposta resposta_resposta_id_key; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.resposta
    ADD CONSTRAINT resposta_resposta_id_key UNIQUE (resposta_id);


--
-- Name: temporada temporada_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.temporada
    ADD CONSTRAINT temporada_pkey PRIMARY KEY (temporada_ano);


--
-- Name: user user_user_id_key; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_user_id_key UNIQUE (user_id);


--
-- Name: user user_user_username_key; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_user_username_key UNIQUE (user_username);


--
-- Name: user usuario_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (user_id);


--
-- Name: post fk_post_categoria1; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT fk_post_categoria1 FOREIGN KEY (post_categoria) REFERENCES public.categoria(categoria_id);


--
-- Name: user fk_usuario_equipe1; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk_usuario_equipe1 FOREIGN KEY (user_equipe) REFERENCES public.equipe(equipe_id);


--
-- Name: user fk_usuario_piloto1; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk_usuario_piloto1 FOREIGN KEY (user_piloto) REFERENCES public.piloto(piloto_id);


--
-- Name: post post_post_usuario_fkey; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_post_usuario_fkey FOREIGN KEY (post_usuario) REFERENCES public."user"(user_username);


--
-- Name: resposta resposta_reposta_pai_id_fkey; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.resposta
    ADD CONSTRAINT resposta_reposta_pai_id_fkey FOREIGN KEY (resposta_pai_id) REFERENCES public.resposta(resposta_id) ON DELETE SET NULL;


--
-- Name: resposta resposta_resposta_categoria_fkey; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.resposta
    ADD CONSTRAINT resposta_resposta_categoria_fkey FOREIGN KEY (resposta_categoria) REFERENCES public.categoria(categoria_id);


--
-- Name: resposta resposta_resposta_post_fkey; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.resposta
    ADD CONSTRAINT resposta_resposta_post_fkey FOREIGN KEY (resposta_post) REFERENCES public.post(post_id);


--
-- Name: resposta resposta_resposta_usuario_fkey; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY public.resposta
    ADD CONSTRAINT resposta_resposta_usuario_fkey FOREIGN KEY (resposta_usuario) REFERENCES public."user"(user_username);


--
-- PostgreSQL database dump complete
--

