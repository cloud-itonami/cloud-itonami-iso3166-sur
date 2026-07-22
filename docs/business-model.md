# Business Model: Independent Public-Sector Market-Entry & Procurement Compliance Service — Suriname

## Classification

- Repository: `cloud-itonami-iso3166-sur`
- ISO 3166: `SUR` (Republic of Suriname)
- Activity: public-procurement market-entry and ongoing regulatory-
  compliance navigation for an already-incorporated operator

## Customer

- an already-incorporated `cloud-itonami-cofog-{code}` /
  `cloud-itonami-isco-{code}` / `cloud-itonami-unspsc-{segment}` /
  `cloud-itonami-{ISIC}` operator wanting to bid on a Surinamese
  government contract
- a foreign SME or civic-tech vendor entering the public sector in
  Suriname for the first time
- a `cloud-itonami-M6910` client that has just completed incorporation
  and now needs public-sector market access
- an investor whose sector (agriculture, animal husbandry, fisheries,
  aquaculture, mining, forestry, tourism excluding casinos) is eligible
  for Investeringswet 2001 facilities via Investsur

## Offer

- procurement-path classification: independent recompute of whether a
  proposed government contract's own SRD value requires an openbare
  aanbesteding (public tender, above SRD 30,000), Raad van Ministers
  (Council of Ministers) approval (above SRD 4,000, up to SRD 30,000),
  or neither (SRD 4,000 or below), per Comptabiliteitsbesluit Artikel
  18 -- and holding the filing until the correct path (tender,
  approval, or presidential exception) is actually on file
- business/tax registration checklist: a Handelsregister extract from
  the Kamer van Koophandel en Fabrieken (KKF), plus a SEPARATE
  Belastingdienst Suriname tax registration (Inkomstenbelasting /
  Loonbelasting / Omzetbelasting)
- Investeringswet 2001 facility-eligibility screening (sector-gated:
  agriculture, animal husbandry, fisheries, aquaculture, mining,
  forestry, tourism excluding casinos) with Investsur filing assistance
- ongoing regulatory-change monitoring subscription
- compliance-audit export package for the client's own records

## Revenue

- per-engagement market-entry fee (one-time registration + checklist
  completion)
- recurring regulatory-change monitoring subscription
- compliance-audit export package

## Trust Controls

- any actual filing submission requires Market-Entry Compliance
  Governor clearance and always escalates to human sign-off
  (`:filing/submit` is never automated at any phase)
- a false or fabricated regulatory-requirement claim is a HARD hold
  that cannot be overridden by human approval alone -- it must be
  corrected against a cited official source first
- a contract whose own SRD value requires an openbare aanbesteding
  (public tender) or Raad van Ministers approval, but does not have
  the correct path on file, is a HARD hold on `:filing/submit` --
  independently recomputed rather than trusted from a self-reported
  flag (the FLAGSHIP check, `procurement-threshold-noncompliant`)
- an unverified Belastingdienst Suriname tax registration is a HARD
  hold on `:filing/submit` -- unconditional
- this service does **not** provide legal or tax advice; characterization
  and filing on the client's behalf beyond checklist/draft assistance
  routes to Suriname-licensed counsel or a registered agent
- this service does **not** claim Suriname has a dedicated procurement
  regulator or e-procurement portal -- neither was found to exist (see
  `docs/adr/0001-architecture.md`); do not "upgrade" this claim without
  a newly verified primary source

## Boundary with adjacent actors (read before forking)

- **`cloud-itonami-M6910`**: helps a client BECOME a legal entity
  (incorporation, ISIC 6910) -- a prior, different regulatory phase
  (company law). This blueprint assumes incorporation is already done and
  handles public-procurement market entry (a different regulatory domain).
- **`cloud-itonami-cofog-{code}`**: a jurisdiction-agnostic operator
  template for ONE public function. This blueprint is the orthogonal
  jurisdiction-specific axis -- the two compose (fork a COFOG-function
  blueprint AND this one to operate in Suriname).
