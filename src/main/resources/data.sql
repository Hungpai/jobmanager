INSERT INTO ACCOUNT (ID, USERNAME, PASSWORD, ROLE) VALUES
    (99, 'Hung', '$2a$10$wDximnD5Vtjq42bGFaYsYOSAlvdyRPyCvljUY8wNNU05fx5gdhFKi', 'USER'),
    (100, 'Alan', '$2a$10$6gtMmvanIwV6EBd9cL1RbOUeHppTyzT/Dk/4cPu2juO.FAO/9HJrm', 'USER');

INSERT INTO SOCIAL_MEDIA (MEDIA_ID, HOMEPAGE_URL, INSTAGRAM_URL, TWITTER_URL) VALUES
    (40, 'https://www.vhv-gruppe.de/de', 'https://www.instagram.com/vhv_versicherungen/?hl=de', 'https://x.com/vhv_gruppe?lang=de'),
    (41, 'https://www.hannover-re.com/de/', 'https://www.instagram.com/hannoverrebachelor/?hl=de', 'https://x.com/hannover_re'),
    (42, 'https://www.enercity.de/', 'https://www.instagram.com/enercity_ag/?hl=de', 'https://x.com/enercity?lang=de'),
    (43, 'https://www.f-i.de/', 'https://www.instagram.com/finanz_informatik/?hl=de', 'https://x.com/fi_ffm?lang=de');

INSERT INTO JOB (JOB_ID, USER_ID, MEDIA_ID, COMPANY, TITLE, STATUS, IMG_URL, JOB_URL) VALUES
    (10, 99, 40, 'VHV','Java-Entwickler', 'ACCEPTED', 'https://pbs.twimg.com/profile_images/730330953529315328/H6yH-DDx_400x400.jpg', 'https://www.vhv-gruppe.de/de/karriere/stellenmarkt/1743051'),
    (12, 99, 41, 'Hannover RE','Software Engineer', 'PENDING', 'https://media.licdn.com/dms/image/v2/D4E0BAQErJTuo0JiYjA/company-logo_200_200/company-logo_200_200/0/1722408907252/hannover_re_logo?e=1740009600&v=beta&t=taYToXYfe2x9xLqiKQ4jkkI1x4fqmGuaOuFxXdPBuBQ', 'https://jobs.hannover-re.com/job/Hannover-Software-Engineer-Application-Integration/954271955/'),
    (13, 99, 42, 'Enercity','IT-Customer Success Manager:in', 'REJECTED', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH_p33_aPQnSDmBmTARp8gBRC7kNuGKZXngg&s', 'https://www.enercity.de/karriere/jobsuche/it-customer-success-manager-in-schwerpunkt-erzeugung-netze-J2024571'),
    (14, 99, 43, 'Finanz Informatik','Java-Entwickler (m/w/d)', 'REJECTED', 'https://pbs.twimg.com/profile_images/1549363241226309634/Rt4jkBjN_400x400.jpg', 'https://www.f-i.de/Karriere/Offene-Stellen/Frankfurt/Java-Entwickler-m-w-d'),
    (15, 100, 40, 'VHV','Java-Entwickler für agiles Team (w/m/d)', 'ACCEPTED', 'https://pbs.twimg.com/profile_images/730330953529315328/H6yH-DDx_400x400.jpg', 'https://www.vhv-gruppe.de/de/karriere/stellenmarkt/1743051'),
    (16, 100, 41, 'Hannover RE','Software Engineer', 'PENDING', 'https://media.licdn.com/dms/image/v2/D4E0BAQErJTuo0JiYjA/company-logo_200_200/company-logo_200_200/0/1722408907252/hannover_re_logo?e=1740009600&v=beta&t=taYToXYfe2x9xLqiKQ4jkkI1x4fqmGuaOuFxXdPBuBQ', 'https://jobs.hannover-re.com/job/Hannover-Software-Engineer-Application-Integration/954271955/'),
    (17, 100, 42, 'Enercity','IT-Customer Success Manager:in', 'REJECTED', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH_p33_aPQnSDmBmTARp8gBRC7kNuGKZXngg&s', 'https://www.enercity.de/karriere/jobsuche/it-customer-success-manager-in-schwerpunkt-erzeugung-netze-J2024571'),
    (18, 100, 43, 'Finanz Informatik','Java-Entwickler (m/w/d)', 'REJECTED', 'https://pbs.twimg.com/profile_images/1549363241226309634/Rt4jkBjN_400x400.jpg', 'https://www.f-i.de/Karriere/Offene-Stellen/Frankfurt/Java-Entwickler-m-w-d');


