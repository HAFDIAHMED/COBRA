class Bank_impl extends BankPOA {
  private float solde;
  Bank_impl(float montant_init) {
    this.solde = montant_init;
  }
public void debiter(float montant_credit) {
    this.solde=this.solde+montant_credit;
  }
  public void crediter(float montant_credit) {
    if(montant_credit<solde) {
      this.solde=this.solde-montant_credit;
    }
  }
  public float montant() {
    return this.solde;
  }
}
