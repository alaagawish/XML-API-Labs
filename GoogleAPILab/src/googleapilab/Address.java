/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleapilab;

/**
 *
 * @author Java-ahmed
 */
class Address {

    private int number;
    private String street;
    private String region;

    Address() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Address *****\n");
        sb.append("Number=" + this.number + "\n");
        sb.append("Street=" + this.street + "\n");
        sb.append("Region=" + this.region + "\n");
        sb.append("*******************\n");
        return sb.toString();
    }
}
