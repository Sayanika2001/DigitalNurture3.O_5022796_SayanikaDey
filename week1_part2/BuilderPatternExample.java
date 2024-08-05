public class BuilderPatternExample {

    
    static class Computer {
        
        private String CPU;
        private String RAM;
        private String storage;
        private String GPU;
        private String motherboard;
        private String powerSupply;
        private String coolingSystem;

        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.GPU = builder.GPU;
            this.motherboard = builder.motherboard;
            this.powerSupply = builder.powerSupply;
            this.coolingSystem = builder.coolingSystem;
        }

        
        public static class Builder {
            
            private String CPU;
            private String RAM;
            private String storage;
            private String GPU;
            private String motherboard;
            private String powerSupply;
            private String coolingSystem;

            
            public Builder setCPU(String CPU) {
                this.CPU = CPU;
                return this;
            }

            public Builder setRAM(String RAM) {
                this.RAM = RAM;
                return this;
            }

            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGPU(String GPU) {
                this.GPU = GPU;
                return this;
            }

            public Builder setMotherboard(String motherboard) {
                this.motherboard = motherboard;
                return this;
            }

            public Builder setPowerSupply(String powerSupply) {
                this.powerSupply = powerSupply;
                return this;
            }

            public Builder setCoolingSystem(String coolingSystem) {
                this.coolingSystem = coolingSystem;
                return this;
            }

            
            public Computer build() {
                return new Computer(this);
            }
        }

        @Override
        public String toString() {
            return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage + ", GPU=" + GPU +
                   ", motherboard=" + motherboard + ", powerSupply=" + powerSupply + ", coolingSystem=" + coolingSystem + "]";
        }
    }

    
    public static void main(String[] args) {
        
        Computer gamingComputer = new Computer.Builder()
            .setCPU("Intel i9")
            .setRAM("32GB")
            .setStorage("1TB SSD")
            .setGPU("NVIDIA RTX 3080")
            .setMotherboard("ASUS ROG")
            .setPowerSupply("750W")
            .setCoolingSystem("Liquid Cooling")
            .build();

        Computer officeComputer = new Computer.Builder()
            .setCPU("Intel i5")
            .setRAM("16GB")
            .setStorage("512GB SSD")
            .setGPU("Integrated")
            .setMotherboard("Gigabyte")
            .setPowerSupply("500W")
            .setCoolingSystem("Air Cooling")
            .build();

        
        System.out.println(gamingComputer);
        System.out.println(officeComputer);
    }
}
