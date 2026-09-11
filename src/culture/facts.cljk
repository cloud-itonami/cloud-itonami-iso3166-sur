(ns culture.facts
  "Country-level regional-culture catalog for Suriname (SUR) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SUR"
   [{:culture/id "sur.dish.pom"
     :culture/name "Pom"
     :culture/country "SUR"
     :culture/kind :dish
     :culture/summary "Surinamese oven dish made using arrowleaf elephant ear root (locally called 'pomtajer'), combined with chicken and citrus juice; a popular party dish."
     :culture/url "https://en.wikipedia.org/wiki/Pom_(dish)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.dish.roti"
     :culture/name "Roti"
     :culture/country "SUR"
     :culture/kind :dish
     :culture/summary "In Suriname, roti refers mainly to dhalpuri or aloo puri and is most often eaten with curried chicken, reflecting the country's Indian-Surinamese community."
     :culture/url "https://en.wikipedia.org/wiki/Roti"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.dish.moksi-alesi"
     :culture/name "Moksi-alesi"
     :culture/country "SUR"
     :culture/kind :dish
     :culture/summary "Surinamese mixed boiled rice dish combining rice with salted meat, shrimp, or fish and vegetables."
     :culture/url "https://en.wikipedia.org/wiki/Culture_of_Suriname"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.beverage.parbo-bier"
     :culture/name "Parbo Bier"
     :culture/country "SUR"
     :culture/kind :beverage
     :culture/summary "5% ABV Surinamese beer brewed by Surinaamse Brouwerij from pale malt, specially cultivated rice, water and hops, holding about 80% domestic market share."
     :culture/url "https://en.wikipedia.org/wiki/Surinaamse_Brouwerij"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.craft.koto"
     :culture/name "Koto"
     :culture/name-local "Kotomisi"
     :culture/country "SUR"
     :culture/kind :craft
     :culture/summary "Traditional dress worn by Afro-Surinamese and Creole women in Suriname, often paired with a head covering called an angisa."
     :culture/url "https://en.wikipedia.org/wiki/Koto_(traditional_clothing)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.festival.ketikoti"
     :culture/name "Ketikoti"
     :culture/country "SUR"
     :culture/kind :festival
     :culture/summary "Annual celebration on 1 July marking Emancipation Day in Suriname, commemorating the abolition of slavery in the country in 1863."
     :culture/url "https://en.wikipedia.org/wiki/Ketikoti"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.heritage.central-suriname-nature-reserve"
     :culture/name "Central Suriname Nature Reserve"
     :culture/country "SUR"
     :culture/kind :heritage
     :culture/summary "Conservation unit preserving an area of pristine tropical rainforest in Suriname, designated a UNESCO World Heritage Site in 2000."
     :culture/url "https://en.wikipedia.org/wiki/Central_Suriname_Nature_Reserve"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sur.heritage.paramaribo"
     :culture/name "Historic Inner City of Paramaribo"
     :culture/country "SUR"
     :culture/kind :heritage
     :culture/summary "The historic inner city of Paramaribo, Suriname's capital, has been a UNESCO World Heritage Site since 2002."
     :culture/url "https://en.wikipedia.org/wiki/Paramaribo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-sur culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SUR"))
                 " SUR entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
