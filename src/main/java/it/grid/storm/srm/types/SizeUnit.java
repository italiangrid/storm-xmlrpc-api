/*
 *
 *  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2006-2010.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * This class represents a unit of measure for FileSize; it contains a conversion factor
 * for changing among units.
 *
 * @author  Ezio Corso
 * @author  EGRID - ICTP Trieste
 * @date    March 23rd, 2005
 * @version 1.0
 */
package it.grid.storm.srm.types;

import java.io.Serializable;
public abstract class SizeUnit implements Serializable {

    public static SizeUnit createSizeUnit(String unit) {
      String input = unit.toLowerCase();
      if (input.toLowerCase().equals("byte")) return SizeUnit.BYTES;
      if (input.toLowerCase().equals("kb")) return SizeUnit.KILOBYTES;
      if (input.toLowerCase().equals("mb")) return SizeUnit.MEGABYTES;
      if (input.toLowerCase().equals("gb")) return SizeUnit.GIGABYTES;
      if (input.toLowerCase().equals("tb")) return SizeUnit.TERABYTES;
      return SizeUnit.EMPTY;
    }

    public static final SizeUnit BYTES = new SizeUnit() {
        public double conversionFactor() {
            return 1.0;
        }

        public String toString() {
            return "Bytes";
        }

        public int hashCode() {
            return 1;
        }
    };

    public static final SizeUnit KILOBYTES = new SizeUnit() {
        public double conversionFactor() {
            return 1024.0;
        }

        public String toString() {
            return "KB";
        }

        public int hashCode() {
            return 2;
        }
    };

    public static final SizeUnit MEGABYTES = new SizeUnit() {
        public double conversionFactor() {
            return 1048576.0;
        }

        public String toString() {
            return "MB";
        }

        public int hashCode() {
            return 3;
        }
    };

    public static final SizeUnit GIGABYTES = new SizeUnit() {
        public double conversionFactor() {
            return SizeUnit.MEGABYTES.conversionFactor()*1024;
        }

        public String toString() {
            return "GB";
        }

        public int hashCode() {
            return 4;
        }
    };

    public static final SizeUnit TERABYTES = new SizeUnit() {
    public double conversionFactor() {
        return SizeUnit.GIGABYTES.conversionFactor()*1024;
    }

    public String toString() {
        return "TB";
    }

    public int hashCode() {
        return 5;
    }
};


    public static final SizeUnit EMPTY = new SizeUnit() {
        public double conversionFactor() {
            return 0.0;
        }

        public String toString() {
            return "EMPTY";
        }

        public int hashCode() {
            return 0;
        }
    };

    private SizeUnit() {}

    /**
     * This method returns a converson factor: the amout of bytes present in
     * 1 unit of this.
     */
    public abstract double conversionFactor();
}
