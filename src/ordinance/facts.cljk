(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Milan (Comune di Milano) --
  a Wave 1b addition per ADR-2607171400 addendum 2, joining the
  cloud-itonami-municipality-* compliance-fact family of ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL comune.milano.it PDF -- never fabricated.
  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/number.

  Both entries below were verified on 2026-07-17 by downloading each
  source PDF from the Comune di Milano site (regulations index:
  'Regolamenti comunali' on www2.comune.milano.it) and directly reading
  the PDF text via the Read tool: each title, deliberation number and
  date stated here appears on the title page of the cited PDF.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"milano"
   [{:ordinance/id "milano.regolamento-edilizio-2014"
     :ordinance/title "Regolamento Edilizio"
     :ordinance/municipality "milano"
     :ordinance/country "ITA"
     :ordinance/kind :ordinance
     :ordinance/number "Adozione: Deliberazione n. 9 del 14/04/2014; Approvazione: Deliberazione n. 27 del 02/10/2014; testo aggiornato con Deliberazione di Giunta n. 2542 del 29/12/2015 e Determinazione Dirigenziale n. 8 del 03/02/2016"
     :ordinance/url "https://www2.comune.milano.it/documents/20126/200621210/Regolamento+Edilizio+2016.pdf/ef520144-6ac6-d3c2-c51e-99c6232fa962?t=1551192584474"
     :ordinance/url-provenance :official-comune-milano-it
     :ordinance/enacted-date "2014-10-02"
     :ordinance/last-revised-date "2016-02-03"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:building :urban-planning}}
    {:ordinance/id "milano.regolamento-benessere-tutela-animali-2020"
     :ordinance/title "Regolamento per il benessere e la tutela degli animali del Comune di Milano"
     :ordinance/municipality "milano"
     :ordinance/country "ITA"
     :ordinance/kind :ordinance
     :ordinance/number "Deliberazione del Consiglio Comunale n. 4 del 3 febbraio 2020"
     :ordinance/url "https://www.comune.milano.it/documents/20118/253355/Regolamento+per+il+Benessere+e+la+tutela+degli+animali+del+Comune+di+Milano.pdf/4c3ebb5e-d8e6-3433-543a-8a7c34f4a5eb?version=1.0&t=1749044016492&download=true"
     :ordinance/url-provenance :official-comune-milano-it
     :ordinance/enacted-date "2020-02-03"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:animal-welfare :public-order}}]})

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
      :note (str "cloud-itonami-municipality-ita-milano Wave 1b (ADR-2607171400 "
                 "addendum 2 / family ADR-2607141700): "
                 (count (get catalog "milano")) " Milano entries seeded with "
                 "official comune.milano.it citations. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
