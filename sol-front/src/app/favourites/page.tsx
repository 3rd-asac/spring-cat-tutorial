"use client";
import Link from "next/link";
import { useEffect, useRef, useState } from "react";
import Image from "next/image";
interface ImageInterface {
  id: string;
  url: string;
  width: number;
  height: number;
  image: {
    id: string;
    url: string;
  };
}

const FavImages = () => {
  const idRef = useRef<HTMLInputElement>(null);
  const [list, setList] = useState<ImageInterface[]>([]);
  const [fav, setFav] = useState<String[]>([]);
  const [isDisabled, setIsDisabled] = useState(true);

  async function getImages() {
    const res = await fetch(
      "http://localhost:8080/api/v1/favourite?page=0&limit=20&sub_id=user-1"
    );
    const data = await res.json();
    console.log(data);
    setList([...data]);
    return data;
  }

  async function clickButton(id: string) {
    //setFav([...fav, id]);
    console.log(id);
    console.log(fav);

    const res = await fetch(
      `http://localhost:8080/api/v1/favourite?favouriteId=${id}`,
      { method: "DELETE" }
    );
    if (res.status === 200) {
      // "DELETE" 요청이 성공하면 상태 코드 204(No Content)를 반환하므로 이를 확인할 수 있습니다.
      console.log("삭제 성공");

      // 원하는 작업을 수행하세요.
    } else {
      // 다른 상태 코드의 경우에 대한 처리를 추가할 수 있습니다.
      console.error("삭제 실패");
    }
    // 여기에서 선택한 이미지 ID(id)를 사용하여 원하는 작업을 수행할 수 있습니다.
  }

  useEffect(() => {
    getImages();
  }, []);

  return (
    <>
      <h1 className="text-3xl"> Cat Photos</h1>
      <Link className="text-2xl" href="/image">
        Cat Images
      </Link>

      <br />
      <div>
        <ul>
          {list.map((li, index) => (
            <li key={li.id}>
              <img
                alt={`${li.id}`}
                src={li.image.url}
                width={`${li.width}`}
                height={`${li.height}`}
              />
              <button onClick={() => clickButton(li.id)}>삭제하기</button>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};

export default FavImages;
