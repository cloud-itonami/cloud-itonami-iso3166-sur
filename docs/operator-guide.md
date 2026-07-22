# Operator Guide

## First Deployment

1. Confirm the client's incorporation/legal-entity status is complete
   (route to `cloud-itonami-M6910` or local counsel first if not).
2. Register the client's intake: the proposed contract's own SRD
   value, target public function, prior filing history in Suriname if
   any.
3. Run the advisor in read-only mode against the Comptabiliteitswet
   1952 / Comptabiliteitsbesluit requirements (there is no dedicated
   procurement act or regulator for Suriname -- do not look for one).
4. Compare the checklist against the client's current documentation:
   - a Handelsregister extract from the Kamer van Koophandel en
     Fabrieken (KKF)
   - a separate Belastingdienst Suriname tax registration
     (Inkomstenbelasting / Loonbelasting / Omzetbelasting, via
     portaal.belastingdienst.sr)
   - the contract's own procurement-path classification: above SRD
     30,000 needs an openbare aanbesteding (public tender) or an
     on-the-record presidential exception; above SRD 4,000 up to SRD
     30,000 needs Raad van Ministers (Council of Ministers) approval;
     SRD 4,000 or below needs neither
   - for an eligible-sector investor (agriculture, animal husbandry,
     fisheries, aquaculture, mining, forestry, tourism excluding
     casinos): Investeringswet 2001 facility request filed with
     Investsur
5. Enable gated filing-draft assistance once the Market-Entry
   Compliance Governor contract is trusted; actual submission always
   requires human sign-off.

## Minimum Production Controls

- client-owned data store for business/tax registration documents
- clear provenance (official law/decree citation) for every
  requirement surfaced
- approval workflow for any filing submission
- independent re-verification that a contract's own SRD value and
  claimed procurement path actually satisfy the Comptabiliteitsbesluit
  Artikel 18 tiers before any `:filing/submit` -- never trust a
  self-reported "tendered" or "approved" flag without checking the
  contract value against the SRD 4,000 / SRD 30,000 thresholds
- named referral relationship with Suriname-licensed counsel or a
  registered agent for anything beyond checklist/draft assistance
- monthly audit export
- disputes/appeals route through the responsible ministry (procurement
  is decentralized per-department under the Comptabiliteitsbesluit;
  there is no dedicated procurement-oversight commission to route to,
  unlike some sibling jurisdictions) -- this actor has no standing to
  file a complaint on the client's behalf

## Certification

Certified operators must prove data provenance, audit traceability, that
automated actions cannot bypass the Market-Entry Compliance Governor, and
a working referral relationship with Suriname-licensed counsel or a
registered agent for whatever licensed representation the law of
Suriname requires for actual filings.
