(ns statute.facts
  "General-law compliance catalog for Suriname (SUR) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company operating in this jurisdiction must generally track for
  compliance. Mirrors cloud-itonami-iso3166-guy/-gnb/-jpn/-deu's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  Every entry cites an OFFICIAL Suriname government-hosted URL -- never
  fabricated. Two hosts served every citation below directly this
  iteration, with no TLS/bot-detection blocker (`curl`/WebFetch with a
  standard browser user-agent succeeded on `dna.sr` and
  `belastingdienst.sr` every time attempted): De Nationale Assemblee's
  own `wetgeving/surinaamse-wetten/` law archive (both its
  'Geldende teksten t/m 2005' and 'Wetten na 2005' categories were
  reviewed in full this session -- ~140 law titles) and the
  Belastingdienst's own site.

  - Handelsregisterwet 1936 (G.B. 1936 no. 149, geldende tekst G.B.
    1962 no. 187, laatstelijk gewijzigd bij S.B. 1981 no. 23) --
    establishes the Handelsregister (Trade Register), maintained by the
    Kamer van Koophandel en Fabrieken (KKF). Its own 1997 fee-schedule
    amendment (S.B. 1997 no. 52, PDF text-layer read directly) confirms
    KKF's OWN separate institutional legal basis in its 'Nota van
    Toelichting': 'Op grond van artikel 30 lid 1 van de \"Wet Kamers
    van Koophandel 1962\" (G.B.1962 No.85) ...'.
  - Wet Kamers van Koophandel 1962 (G.B. 1962 no. 85) -- establishes
    the Kamer(s) van Koophandel en Fabrieken (KKF), cited via the same
    1997 amendment's Nota van Toelichting above.
  - Comptabiliteitswet 1952 (G.B. 1952 no. 111, amended G.B. 1955 no.
    38 / G.B. 1969 no. 58 / S.B. 2003 no. 76) -- Suriname's general
    public-finance law; its own Artikel 18 mandates public tendering
    (openbare aanbesteding) of government works/supplies/transport, the
    basis Suriname uses INSTEAD of a dedicated 'aanbestedingswet' (none
    exists -- verified by exhaustively reviewing DNA's own complete law
    index, see `marketentry.facts` namespace docstring).
  - Comptabiliteitsbesluit (G.B. 1953 no. 100, amended through S.B.
    2004 no. 116) -- the Comptabiliteitswet's implementing decree; its
    own Artikel 18 (as amended) carries the SRD 4,000 / SRD 30,000
    procurement thresholds that ground this repo's flagship governor
    check (see `marketentry.registry`/`marketentry.governor`).
  - Investeringswet 2001 (S.B. 2002 no. 42, 'WET van 3 juni 2002')
    -- Suriname's investment-promotion law; establishes fiscal/non-
    fiscal facilities for investment in agriculture, animal husbandry,
    fisheries, aquaculture, mining, forestry and tourism (excluding
    casino operation); requests are filed with Investsur (Instituut
    ter bevordering van investeringen, established by a companion law
    of the same date, S.B. 2002 no. 41, per the Investeringswet's own
    Artikel 1(1)(b)).
  - Inkomstenbelasting 1922 (G.B. 1921 no. 122, geldende tekst G.B.
    1960 no. 84, laatstelijk gewijzigd bij S.B. 2000 no. 123) --
    Suriname's Income Tax Act, cited via its own reference inside the
    Investeringswet 2001 text.
  - Wet Loonbelasting (S.B. 1981 no. 181, geldende tekst S.B. 1985 no.
    10, laatstelijk gewijzigd bij S.B. 1994 no. 88) -- Suriname's Wage
    Tax Act, cited via the same Investeringswet 2001 reference chain.
  - Wet Omzetbelasting 1997 (S.B. 1997 no. 87) -- Suriname's turnover
    (VAT-equivalent) tax act, cited via the same Investeringswet 2001
    reference chain; also the 'BTW' Belastingdienst's own site
    (belastingdienst.sr) names as one of the taxes registered/filed via
    its `portaal.belastingdienst.sr/OPO` portal.
  - Arbeidswet 1963 (G.B. 1963 no. 163, amended S.B. 1980 no. 116 /
    S.B. 1983 no. 91 / S.B. 2001 no. 71) -- Suriname's Labour Act, own
    text (PDF text-layer read directly via the Read tool): Artikel 3
    lid 1 caps ordinary working hours at 8.5 hours/day or 48 hours/
    week; Artikel 1 defines 'jeugdige personen' (14 to under 18) and
    'kinderen' (under 14, or under 15 for those aboard fishing vessels
    per the Zeevisserij Decreet 1980).

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"SUR"
   [{:statute/id "sur.handelsregisterwet-1936"
     :statute/title "Handelsregisterwet 1936"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "G.B. 1936 no. 149 (geldende tekst G.B. 1962 no. 187, laatstelijk gewijzigd bij S.B. 1981 no. 23; kostenschema laatstelijk gewijzigd bij S.B. 1997 no. 52)"
     :statute/url "https://www.dna.sr/media/ae1hho5c/sb_1997_no_52_wet_handelsregisterwet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1936-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "sur.wet-kamers-van-koophandel-1962"
     :statute/title "Wet Kamers van Koophandel 1962"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "G.B. 1962 no. 85"
     :statute/url "https://www.dna.sr/media/ae1hho5c/sb_1997_no_52_wet_handelsregisterwet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1962-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance}}
    {:statute/id "sur.comptabiliteitswet-1952"
     :statute/title "Comptabiliteitswet 1952"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "G.B. 1952 no. 111 (amended G.B. 1955 no. 38, G.B. 1969 no. 58, S.B. 2003 no. 76)"
     :statute/url "https://www.dna.sr/media/5ioj0qc3/comptabiliteitswet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1952-12-02"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:public-finance :procurement}}
    {:statute/id "sur.comptabiliteitsbesluit"
     :statute/title "Comptabiliteitsbesluit"
     :statute/jurisdiction "SUR"
     :statute/kind :decree
     :statute/law-number "G.B. 1953 no. 100 (amended through S.B. 2004 no. 116)"
     :statute/url "https://www.dna.sr/media/hexjjg1r/comptabiliteitsbesluit.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1953-08-27"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:public-finance :procurement}}
    {:statute/id "sur.investeringswet-2001"
     :statute/title "Investeringswet 2001"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "S.B. 2002 no. 42"
     :statute/url "https://www.dna.sr/media/ggxkr25o/investeringswet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "2002-06-03"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:investment}}
    {:statute/id "sur.inkomstenbelasting-1922"
     :statute/title "Inkomstenbelasting 1922"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "G.B. 1921 no. 122 (geldende tekst G.B. 1960 no. 84, laatstelijk gewijzigd bij S.B. 2000 no. 123)"
     :statute/url "https://www.dna.sr/media/ggxkr25o/investeringswet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1922-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}
    {:statute/id "sur.wet-loonbelasting"
     :statute/title "Wet Loonbelasting"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "S.B. 1981 no. 181 (geldende tekst S.B. 1985 no. 10, laatstelijk gewijzigd bij S.B. 1994 no. 88)"
     :statute/url "https://www.dna.sr/media/ggxkr25o/investeringswet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1981-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax :labor}}
    {:statute/id "sur.wet-omzetbelasting-1997"
     :statute/title "Wet Omzetbelasting 1997"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "S.B. 1997 no. 87"
     :statute/url "https://belastingdienst.sr/"
     :statute/url-provenance :official-belastingdienst-sr
     :statute/enacted-date "1997-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}
    {:statute/id "sur.arbeidswet-1963"
     :statute/title "Arbeidswet 1963"
     :statute/jurisdiction "SUR"
     :statute/kind :law
     :statute/law-number "G.B. 1963 no. 163 (amended S.B. 1980 no. 116, S.B. 1983 no. 91, S.B. 2001 no. 71)"
     :statute/url "https://www.dna.sr/media/cwabf0np/arbeidswet.pdf"
     :statute/url-provenance :official-dna-sr
     :statute/enacted-date "1963-12-19"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-sur statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "SUR")) " SUR statutes seeded with an "
                 "official government-hosted citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :tax)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
