package sortingAlgorithms;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

class SortingAlgorithms implements SortingInterface {

    public void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        //ascending
        //ascending
        if (reversed == false) {
            for (int i = lowindex + 1; i <= highindex; i++) {
                int index = i - 1;
                Comparable temp = array[i];
                while (index >= lowindex && array[index].compareTo(temp) == 1) {
                    array[index + 1] = array[index];
                    index--;
                }
                array[index + 1] = temp;
            }
        } else {
            for (int i = lowindex + 1; i <= highindex; i++) {
                int index = i - 1;
                Comparable temp = array[i];
                while (index >= lowindex && array[index].compareTo(temp) == -1) {
                    array[index + 1] = array[index];
                    index--;
                }
                array[index + 1] = temp;
            }
        }
    }

    public void shakerSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        //ascending
        if (reversed == false) {
            for (int i = lowindex + 1; i <= (highindex - lowindex + 1) / 2 + lowindex; i++) {
                for (int m = i - 1; m <= highindex + 1 - i + lowindex; m++) {
                    if (m + 1 <= highindex && array[m].compareTo(array[m + 1]) == 1) {
                        swap(array, m, m + 1);
                    }
                }
                for (int n = highindex - i + lowindex; n >= i; n--) {
                    if (n > lowindex && array[n - 1].compareTo(array[n]) == 1) {
                        swap(array, n, n - 1);
                    }
                }
            }
        } else {
            for (int i = lowindex + 1; i <= (highindex - lowindex + 1) / 2 + lowindex; i++) {
                for (int m = i - 1; m <= highindex + 1 - i + lowindex; m++) {
                    if (m + 1 <= highindex && array[m].compareTo(array[m + 1]) == -1) {
                        swap(array, m, m + 1);
                    }
                }
                for (int n = highindex - i + lowindex; n >= i; n--) {
                    if (n > lowindex && array[n - 1].compareTo(array[n]) == -1) {
                        swap(array, n, n - 1);
                    }
                }
            }
        }
    }

    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        if (lowindex < highindex) {
            int p = partition(array, lowindex, highindex, reversed);
            randomizedQuickSort(array, lowindex, p - 1, reversed);
            randomizedQuickSort(array, p + 1, highindex, reversed);
        }
    }

    public int partition(Comparable[] array, int left, int right, boolean reversed) {
        if (reversed == false) {
            Random random = new Random();
            int r = random.nextInt(right - left) + left;
            swap(array, right, r);
            Comparable x = array[right];
            int p = left - 1;
            for (int i = left; i < right; i++) {
                if (array[i].compareTo(x) == -1 || array[i].equals(x)) {
                    p++;
                    swap(array, p, i);
                }
            }
            swap(array, p + 1, right);
            return p + 1;
        } else {
            Random random = new Random();
            int r = random.nextInt(right - left) + left;
            swap(array, right, r);
            Comparable x = array[right];
            int p = left - 1;
            for (int i = left; i < right; i++) {
                if (array[i].compareTo(x) == 1 || array[i].equals(x)) {
                    p++;
                    swap(array, p, i);
                }
            }
            swap(array, p + 1, right);
            return p + 1;
        }
    }

    public void hybridSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        int length = highindex - lowindex + 1;
        if (length < 25) {
            insertionSort(array, lowindex, highindex, reversed);
        } else {
            QuickSort(array, lowindex, highindex, reversed);
        }
    }

    public void QuickSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        if (lowindex < highindex) {
            if (highindex - lowindex + 1 > 25) {
                int p = partition(array, lowindex, highindex, reversed);
                QuickSort(array, lowindex, p - 1, reversed);
                QuickSort(array, p + 1, highindex, reversed);
            } else {
                insertionSort(array, lowindex, highindex, reversed);
            }
        }
    }

    public void hybridSortB(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        QuickSortB(array, lowindex, highindex, reversed);
    }

    public void QuickSortB(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        if (lowindex < highindex) {
            if (highindex - lowindex + 1 > 10) {
                int p = partition(array, lowindex, highindex, reversed);
                QuickSortB(array, lowindex, p - 1, reversed);
                QuickSortB(array, p + 1, highindex, reversed);
            } else {
                insertionSort(array, lowindex, highindex, reversed);
            }
        }
    }

    public void externalSort(String inputFile, String outputFile, int n, int k) {
        int length;
        if (n % k == 0) length = n / k;
        else length = n / k + 1;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            Files.deleteIfExists(Paths.get(outputFile));
            String line;
            for (int i = 0; i < length; i++) {
                int lineNum = 0;
                Integer[] array = new Integer[k];
                while ((lineNum < k) && (line = br.readLine()) != null) {
                    array[lineNum] = Integer.parseInt(line);
                    lineNum++;
                }
                insertionSort(array, 0, k - 1, false);
                String file = "temp" + String.valueOf(i) + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file), true));
                for (int j = 0; j < lineNum; j++) {
                    writer.write(String.valueOf(array[j]) + "\n");
                }
                writer.close();
            }
            String line2;
            Integer[] a = new Integer[length];
            boolean[] flag = new boolean[length];
            BufferedReader[] brs = new BufferedReader[length];
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFile), true));
            for (int i = 0; i < length; i++) {
                flag[i] = false;
                String file1 = "temp" + String.valueOf(i) + ".txt";
                brs[i] = new BufferedReader(new FileReader(file1));
                a[i] = Integer.parseInt(brs[i].readLine());
            }
            while (true) {
                int index = -1, min = Integer.MAX_VALUE;
                for (int i = 0; i < length; i++) {
                    if (a[i] < min) {
                        index = i;
                        min = a[i];
                    }
                }
                if (index == -1) break;
                System.out.println(min);
                writer.write(String.valueOf(min) + "\r\n");
                if (flag[index] == false) {
                    if ((line2 = brs[index].readLine()) == null) {
                        flag[index] = true;
                        a[index] = Integer.MAX_VALUE;
                    } else {
                        a[index] = Integer.parseInt(line2);
                    }
                }
            }
            writer.close();
            for (int i = 0; i < length; i++) {
                brs[i].close();
            }
            // Delete temp files
            for (int i = 0; i < 100; i++) {
                Files.deleteIfExists(Paths.get("temp" + i + ".txt"));
                Files.deleteIfExists(Paths.get("tmp" + i + ".txt"));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + e);
        } catch (IOException e) {
            System.out.println("Could not read from file: " + e);
        }
    }

    public Node mergeSortLL(Node list, boolean reversed) {
        if (list == null || list.next() == null) return list;
        Node mid = getMid(list);
        Node right = mid.next();
        mid.setNext(null);
        return mergeSort(mergeSortLL(list, reversed), mergeSortLL(right, reversed), reversed);
    }

    public Node getMid(Node head) {
        if (head == null || head.next() == null) return head;
        Node slow = head, quick = head;
        while (quick.next() != null && quick.next().next() != null) {
            slow = slow.next();
            quick = quick.next().next();
        }
        return slow;
    }

    public Node mergeSort(Node head1, Node head2, boolean reversed) {
        if (reversed == false) {
            Node p1 = head1, p2 = head2, head;
            if (head1.elem() < head2.elem()) {
                head = head1;
                p1 = p1.next();
            } else {
                head = head2;
                p2 = p2.next();
            }
            Node p = head;
            while (p1 != null && p2 != null) {
                if (p1.elem() <= p2.elem()) {
                    p.setNext(p1);
                    p1 = p1.next();
                    p = p.next();
                } else {
                    p.setNext(p2);
                    p2 = p2.next();
                    p = p.next();
                }
            }
            if (p1 != null) {
                p.setNext(p1);
            }
            if (p2 != null) {
                p.setNext(p2);
            }
            return head;
        } else {
            Node p1 = head1, p2 = head2, head;
            if (head1.elem() > head2.elem()) {
                head = head1;
                p1 = p1.next();
            } else {
                head = head2;
                p2 = p2.next();
            }
            Node p = head;
            while (p1 != null && p2 != null) {
                if (p1.elem() >= p2.elem()) {
                    p.setNext(p1);
                    p1 = p1.next();
                    p = p.next();
                } else {
                    p.setNext(p2);
                    p2 = p2.next();
                    p = p.next();
                }
            }
            if (p1 != null) {
                p.setNext(p1);
            }
            if (p2 != null) {
                p.setNext(p2);
            }
            return head;
        }
    }
}