(ns culture.facts
  "Regional-culture catalog for Milan (Comune di Milano) -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"milano"
   [{:culture/id "milano.dish.risotto-alla-milanese"
     :culture/name "Risotto alla milanese"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Risotto flavoured with saffron, a dish of Milanese tradition; the classic version is made with broth and saffron, and it is the traditional accompaniment to ossobuco."
     :culture/url "https://en.wikipedia.org/wiki/Risotto_alla_milanese"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.dish.cotoletta-alla-milanese"
     :culture/name "Cotoletta alla milanese"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Breaded veal cutlet fried in butter, originating from the city of Milan and part of Milanese Lombard cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Cotoletta_alla_milanese"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.dish.ossobuco"
     :culture/name "Ossobuco"
     :culture/name-local "Ossobuco alla milanese"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Lombard specialty of cross-cut veal shanks braised with vegetables, white wine and broth, often garnished with gremolata; traditionally served with risotto alla milanese."
     :culture/url "https://en.wikipedia.org/wiki/Ossobuco"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.dish.panettone"
     :culture/name "Panettone"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Italian sweet bread that originated in Milan, usually prepared for Christmas and New Year; the name derives from the Milanese dialect."
     :culture/url "https://en.wikipedia.org/wiki/Panettone"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.dish.cassoeula"
     :culture/name "Cassoeula"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Winter dish of pork and Savoy cabbage popular in western Lombardy, described as a noble, ancient Milanese dish; traditionally eaten after the first frost of the season."
     :culture/url "https://en.wikipedia.org/wiki/Cassoeula"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.product.gorgonzola"
     :culture/name "Gorgonzola"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :product
     :culture/summary "Italian blue cheese made from unskimmed cow's milk, named after the Lombardian town of Gorgonzola near Milan where it originated; Italian DOC status since 1955 and EU PDO since 1996."
     :culture/url "https://en.wikipedia.org/wiki/Gorgonzola"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.beverage.barbajada"
     :culture/name "Barbajada"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :beverage
     :culture/summary "Milanese beverage of chocolate, milk, coffee and sugar whipped until frothy, emblematic of 19th-century Milanese café culture; it remained in vogue until the 1930s."
     :culture/url "https://it.wikipedia.org/wiki/Barbajada"
     :culture/url-provenance :wikipedia-it
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.festival.oh-bej-oh-bej"
     :culture/name "Oh bej! Oh bej!"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :festival
     :culture/summary "Milan's most important traditional Christmas fair, held from 7 December (Saint Ambrose Day, the city's patron saint) to the following Sunday; located at the Sforza Castle since 2006."
     :culture/url "https://en.wikipedia.org/wiki/Oh_bej!_Oh_bej!"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "milano.heritage.duomo-di-milano"
     :culture/name "Milan Cathedral"
     :culture/name-local "Duomo di Milano"
     :culture/municipality "milano"
     :culture/country "ITA"
     :culture/kind :heritage
     :culture/summary "Cathedral church of Milan and the largest church in the Italian Republic; construction began in 1386 and took nearly six centuries, with final details completed in 1965."
     :culture/url "https://en.wikipedia.org/wiki/Milan_Cathedral"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-ita-milano culture catalog "
                 "(ADR-2607171400): " (count (get catalog "milano"))
                 " Milano entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
