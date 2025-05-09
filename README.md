# PBOPROJECT_HarvestMoon


-> PLAYER
    -> func & proc
        public void setAnimation(String animation) -> ini buat ganti animasi string pake yang ada di list animasi player
        public void changePath(String bagian, String nama) -> ini bagian kek (body, eye, outfit, hair), buat kalo ada ganti tampilan
        public String getPath() -> ini get path terbaru dari tamplilan player
        public void update() -> update data player kayak ganti animasi, jalan, atau action apapun 


    -> List Animasi Player (nambahnya di declare 1-1 di constructor player)
    0 : Walk
    1 : Idle


